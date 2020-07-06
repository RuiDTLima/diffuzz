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
read -p "Do you really want to run the complete evaluation? It will take around **15 hours**? " -n 1 -r
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
"blazer_sanity_safe"  # MicroBench
"blazer_sanity_unsafe"
"blazer_sanity_unsafe_corrected"
"apache_ftpserver_stringutils_safe" # Zero Day
"apache_ftpserver_stringutils_unsafe"
"apache_ftpserver_stringutils_unsafe_corrected"
)

declare -a classpaths=(
"./bin-instr/" # "blazer_sanity_safe"
"./bin-instr/" # "blazer_sanity_unsafe"
"./bin-instr/" # "blazer_sanity_unsafe_corrected"
"./bin-instr/:./lib/*" # "apache_ftpserver_stringutils_safe"
"./bin-instr/:./lib/*" # "apache_ftpserver_stringutils_unsafe"
"./bin-instr/:./lib/*" # "apache_ftpserver_stringutils_unsafe_corrected"
)

declare -a drivers=(
"Sanity_FuzzDriver" # "blazer_sanity_safe"
"Sanity_FuzzDriver" # "blazer_sanity_unsafe"
"Sanity_FuzzDriver" # "blazer_sanity_unsafe_corrected"
"Driver_StringUtilsPad" # "apache_ftpserver_stringutils_safe"
"Driver_StringUtilsPad" # "apache_ftpserver_stringutils_unsafe"
"Driver_StringUtilsPad" # "apache_ftpserver_stringutils_unsafe_corrected"
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
