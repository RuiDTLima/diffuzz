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
read -p "Do you really want to run the complete evaluation? It will take around **4 days**? " -n 1 -r
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
"apache_ftpserver_stringutils_safe" 
"apache_ftpserver_stringutils_unsafe"
"apache_ftpserver_stringutils_unsafe_corrected"
"blazer_sanity_safe" 
"blazer_sanity_unsafe"
"blazer_sanity_unsafe_corrected"
"example_PWCheck_safe"
"example_PWCheck_unsafe"
"example_PWCheck_unsafe_corrected"
"github_authmreloaded_safe"
"github_authmreloaded_unsafe"
"github_authmreloaded_unsafe_corrected"
"themis_boot-stateless-auth_safe"
"themis_boot-stateless-auth_unsafe"
"themis_boot-stateless-auth_unsafe_corrected"
"themis_dynatable_unsafe"
"themis_dynatable_unsafe_corrected"
"themis_jdk_safe"
"themis_jdk_unsafe"
"themis_jdk_unsafe_corrected"
"themis_jetty_safe" 
"themis_jetty_unsafe"
"themis_jetty_unsafe_corrected"
"themis_oacc_unsafe"
"themis_oacc_unsafe_corrected"
"themis_orientdb_safe"
"themis_orientdb_unsafe"
"themis_orientdb_unsafe_corrected"
"themis_picketbox_safe" 
"themis_picketbox_unsafe"
"themis_picketbox_unsafe_corrected"
"themis_spring-security_safe"
"themis_spring-security_unsafe"
"themis_spring-security_unsafe_corrected"
"themis_tomcat_safe"
"themis_tomcat_unsafe"
"themis_tomcat_unsafe_corrected"
)

declare -a classpaths=(
"./bin-instr/:./lib/*" # "apache_ftpserver_stringutils_safe"
"./bin-instr/:./lib/*" # "apache_ftpserver_stringutils_unsafe"
"./bin-instr/:./lib/*" # "apache_ftpserver_stringutils_unsafe_corrected"
"./bin-instr/" # "blazer_sanity_safe"
"./bin-instr/" # "blazer_sanity_unsafe"
"./bin-instr/" # "blazer_sanity_unsafe_corrected"
"./bin-instr/" # "example_PWCheck_safe"
"./bin-instr/" # "example_PWCheck_unsafe"
"./bin-instr/" # "example_PWCheck_unsafe_corrected"
"./bin-instr/:./lib/*" # "github_authmreloaded_safe"
"./bin-instr/:./lib/*" # "github_authmreloaded_unsafe"
"./bin-instr/:./lib/*" # "github_authmreloaded_unsafe_corrected"
"./bin-instr/:./lib/*" # "themis_boot-stateless-auth_safe"
"./bin-instr/:./lib/*" # "themis_boot-stateless-auth_unsafe"
"./bin-instr/:./lib/*" # "themis_boot-stateless-auth_unsafe_corrected"
"./bin-instr/:./lib/*" # "themis_dynatable_unsafe"
"./bin-instr/:./lib/*" # "themis_dynatable_unsafe_corrected"
"./bin-instr/" # "themis_jdk_safe"
"./bin-instr/" # "themis_jdk_unsafe"
"./bin-instr/" # "themis_jdk_unsafe_corrected"
"./bin-instr/" # "themis_jetty_safe"
"./bin-instr/" # "themis_jetty_unsafe"
"./bin-instr/" # "themis_jetty_unsafe_corrected"
"./bin-instr/:./lib/*" # "themis_oacc_unsafe"
"./bin-instr/:./lib/*" # "themis_oacc_unsafe_corrected"
"./bin-instr/:./lib/*" # "themis_orientdb_safe"
"./bin-instr/:./lib/*" # "themis_orientdb_unsafe"
"./bin-instr/:./lib/*" # "themis_orientdb_unsafe_corrected"
"./bin-instr/" # "themis_picketbox_safe"
"./bin-instr/" # "themis_picketbox_unsafe"
"./bin-instr/" # "themis_picketbox_unsafe_corrected"
"./bin-instr/" # "themis_spring-security_safe"
"./bin-instr/" # "themis_spring-security_unsafe"
"./bin-instr/" # "themis_spring-security_unsafe_corrected"
"./bin-instr/:./lib/*" # "themis_tomcat_safe"
"./bin-instr/:./lib/*" # "themis_tomcat_unsafe"
"./bin-instr/:./lib/*" # "themis_tomcat_unsafe_corrected"
)

declare -a drivers=(
"Driver_StringUtilsPad" # "apache_ftpserver_stringutils_safe"
"Driver_StringUtilsPad" # "apache_ftpserver_stringutils_unsafe"
"Driver_StringUtilsPad" # "apache_ftpserver_stringutils_unsafe_corrected"
"Sanity_FuzzDriver" # "blazer_sanity_safe"
"Sanity_FuzzDriver" # "blazer_sanity_unsafe"
"Sanity_FuzzDriver" # "blazer_sanity_unsafe_corrected"
"Driver" # "example_PWCheck_safe"
"Driver" # "example_PWCheck_unsafe"
"Driver" # "example_PWCheck_unsafe_corrected"
"Driver" # "github_authmreloaded_safe"
"Driver" # "github_authmreloaded_unsafe"
"Driver" # "github_authmreloaded_unsafe_corrected"
"Driver" # "themis_boot-stateless-auth_safe"
"Driver" # "themis_boot-stateless-auth_unsafe"
"Driver" # "themis_boot-stateless-auth_unsafe_corrected"
"Driver" # "themis_dynatable_unsafe"
"Driver" # "themis_dynatable_unsafe_corrected"
"MessageDigest_FuzzDriver" # "themis_jdk_safe"
"MessageDigest_FuzzDriver" # "themis_jdk_unsafe"
"MessageDigest_FuzzDriver" # "themis_jdk_unsafe_corrected"
"Credential_FuzzDriver" # "themis_jetty_safe"
"Credential_FuzzDriver" # "themis_jetty_unsafe"
"Credential_FuzzDriver" # "themis_jetty_unsaf_correctede"
"Driver" # "themis_oacc_unsafe"
"Driver" # "themis_oacc_unsafe_corrected"
"OSecurityManager_FuzzDriver" # "themis_orientdb_safe"
"OSecurityManager_FuzzDriver" # "themis_orientdb_unsafe"
"OSecurityManager_FuzzDriver" # "themis_orientdb_unsafe_corrected"
"UsernamePasswordLoginModule_FuzzDriver" # "themis_picketbox_safe"
"UsernamePasswordLoginModule_FuzzDriver" # "themis_picketbox_unsafe"
"UsernamePasswordLoginModule_FuzzDriver" # "themis_picketbox_unsafe_corrected"
"PasswordEncoderUtils_FuzzDriver" # "themis_spring-security_safe"
"PasswordEncoderUtils_FuzzDriver" # "themis_spring-security_unsafe"
"PasswordEncoderUtils_FuzzDriver" # "themis_spring-security_unsafe_corrected"
"Tomcat_FuzzDriver" # "themis_tomcat_safe"
"Tomcat_FuzzDriver" # "themis_tomcat_unsafe"
"Tomcat_FuzzDriver" # "themis_tomcat_unsafe_corrected"
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
    AFL_I_DONT_CARE_ABOUT_MISSING_CRASHES=1 AFL_SKIP_CPUFREQ=1 nohup ../../../tool/afl-2.51b-wca/afl-fuzz -i in_dir -o fuzzer-out-$j -c userdefined -S afl -t 999999999 ../../../tool/fuzzerside/interface @@ > ./afl-log-$j.txt &
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
