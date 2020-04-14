## run_complete_evaluation.sh
# CAUTION
# Run this script within its folder. Otherwise the paths might be wrong!
#####################################
# chmod +x run_complete_evaluation.sh
# ./run_complete_evaluation.sh
#

trap "exit" INT

# Ask user.
# 58 subjects, 5 times, 30min
read -p "Do you really want to run the complete evaluation? It will take around **2 days**? " -n 1 -r
echo
if [[ ! $REPLY =~ ^[Yy]$ ]]
then
  echo "ABORT."
  exit 1
fi

#####################

echo "Run complete evaluation..."

number_of_runs=5
time_bound=1800 #30min
step_size_eval=30

declare -a subjects=(
"blazer_passwordEq_safe"
"blazer_passwordEq_unsafe"
"example_PWCheck_safe"
"example_PWCheck_unsafe"
"themis_jdk_safe"
"themis_jdk_unsafe"
"themis_tomcat_safe"
"themis_tomcat_unsafe"
"themis_orientdb_safe"
"themis_orientdb_unsafe"
"themis_boot-stateless-auth_safe"
"themis_boot-stateless-auth_unsafe"
"themis_oacc_unsafe"
)

declare -a classpaths=(
"./bin-instr/" # "blazer_passwordEq_safe"
"./bin-instr/" # "blazer_passwordEq_unsafe"
"./bin-instr/" # "example_PWCheck_safe"
"./bin-instr/" # "example_PWCheck_unsafe"
"./bin-instr/" # "themis_jdk_safe"
"./bin-instr/" # "themis_jdk_unsafe"
"./bin-instr/:./lib/*" # "themis_tomcat_safe"
"./bin-instr/:./lib/*" # "themis_tomcat_unsafe"
"./bin-instr/:./lib/*" # "themis_orientdb_safe"
"./bin-instr/:./lib/*" # "themis_orientdb_unsafe"
"./bin-instr/:./lib/*" # "themis_boot-stateless-auth_safe"
"./bin-instr/:./lib/*" # "themis_boot-stateless-auth_unsafe"
"./bin-instr/:./lib/*" # "themis_oacc_unsafe"
)

declare -a drivers=(
"User_FuzzDriver" # "blazer_passwordEq_safe"
"User_FuzzDriver" # "blazer_passwordEq_unsafe"
"Driver" # "example_PWCheck_safe"
"Driver" # "example_PWCheck_unsafe"
"MessageDigest_FuzzDriver" # "themis_jdk_safe"
"MessageDigest_FuzzDriver" # "themis_jdk_unsafe"
"Tomcat_FuzzDriver" # "themis_tomcat_safe"
"Tomcat_FuzzDriver" # "themis_tomcat_unsafe"
"OSecurityManager_FuzzDriver" # "themis_orientdb_safe"
"OSecurityManager_FuzzDriver" # "themis_orientdb_unsafe"
"Driver2" # "themis_boot-stateless-auth_safe"
"Driver2" # "themis_boot-stateless-auth_unsafe"
"Driver" # "themis_oacc_unsafe"
)

# Check array sizes
if [[ ${#subjects[@]} != ${#classpaths[@]} ]]
then
echo "[Error in script] the array sizes of subjects and classpaths do not match!. Abort!"
exit 1
fi
if [[ ${#subjects[@]} != ${#drivers[@]} ]]
then
echo "[Error in script] the array sizes of subjects and drivers do not match!. Abort!"
exit 1
fi

subject_counter=0
total_number_subjects=${#subjects[@]}
total_number_experiments=$(( $total_number_subjects * $number_of_runs))

for (( i=0; i<=$(( $total_number_subjects - 1 )); i++ ))
do
  cd ./${subjects[i]}/

  for j in `seq 1 $number_of_runs`
  do

    run_counter=$(( $run_counter + 1 ))
    echo "[$run_counter/$total_number_experiments] Run analysis for ${subjects[i]}, round $j .."

    # Start Kelinci server
    nohup java -cp ${classpaths[i]} edu.cmu.sv.kelinci.Kelinci ${drivers[i]} @@ > ./server-log-$j.txt &
    server_pid=$!

    # Start modified AFL
    AFL_I_DONT_CARE_ABOUT_MISSING_CRASHES=1 AFL_SKIP_CPUFREQ=1 nohup ../../tool/afl-2.51b-wca/afl-fuzz -i in_dir -o fuzzer-out-$j -c userdefined -S afl -t 999999999 ../../tool/fuzzerside/interface @@ > ./afl-log-$j.txt &
    afl_pid=$!

    # Wait for timebound
    sleep $time_bound

    # Stop AFL and Kelinci server
    kill $afl_pid
    kill $server_pid

    # Wait a little bit to make sure that processes are killed
    sleep 10

  done

  cd ..

  # Evaluate run
  python3 evaluate_cost.py ${subjects[i]}/fuzzer-out- $number_of_runs $time_bound $step_size_eval

done
