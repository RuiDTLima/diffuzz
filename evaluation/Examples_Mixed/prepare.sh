## prepare.sh
#####################################
# chmod +x prepare.sh
# ./prepare.sh
#

trap "exit" INT

# Prepare apache_ftpserver_stringutils_safe.
echo "Prepare apache_ftpserver_stringutils_safe.."
cd ./apache_ftpserver_stringutils_safe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar:../lib/* Driver_StringUtilsPad.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar:./lib/* edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare apache_ftpserver_stringutils_unsafe.
echo "Prepare apache_ftpserver_stringutils_unsafe.."
cd ./apache_ftpserver_stringutils_unsafe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar:../lib/* Driver_StringUtilsPad.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar:./lib/* edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare apache_ftpserver_stringutils_unsafe_corrected.
echo "Prepare apache_ftpserver_stringutils_unsafe_corrected.."
cd ./apache_ftpserver_stringutils_unsafe_corrected/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar:../lib/* Driver_StringUtilsPad.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar:./lib/* edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare blazer_sanity_safe.
echo "Prepare blazer_sanity_safe.."
cd ./blazer_sanity_safe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar Sanity_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare blazer_sanity_unsafe.
echo "Prepare blazer_sanity_unsafe.."
cd ./blazer_sanity_unsafe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar Sanity_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare blazer_sanity_unsafe_corrected.
echo "Prepare blazer_sanity_unsafe_corrected.."
cd ./blazer_sanity_unsafe_corrected/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar Sanity_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare example_PWCheck_safe.
echo "Prepare example_PWCheck_safe.."
cd ./example_PWCheck_safe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar Driver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare example_PWCheck_unsafe.
echo "Prepare example_PWCheck_unsafe.."
cd ./example_PWCheck_unsafe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar Driver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare example_PWCheck_unsafe_corrected.
echo "Prepare example_PWCheck_unsafe_corrected.."
cd ./example_PWCheck_unsafe_corrected/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar Driver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare github_authmreloaded_safe.
echo "Prepare github_authmreloaded_safe.."
cd ./github_authmreloaded_safe/
rm -rf bin
mkdir bin
cd src/main/java/
javac -cp .:../../../../../../tool/instrumentor/build/libs/kelinci.jar:../../../lib/* Driver.java -d ../../../bin
cd ../../../
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar:./lib/* edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare github_authmreloaded_unsafe.
echo "Prepare github_authmreloaded_unsafe.."
cd ./github_authmreloaded_unsafe/
rm -rf bin
mkdir bin
cd src/main/java/
javac -cp .:../../../../../../tool/instrumentor/build/libs/kelinci.jar:../../../lib/* Driver.java -d ../../../bin
cd ../../../
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar:./lib/* edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare github_authmreloaded_unsafe_corrected.
echo "Prepare github_authmreloaded_unsafe_corrected.."
cd ./github_authmreloaded_unsafe_corrected/
rm -rf bin
mkdir bin
cd src/main/java/
javac -cp .:../../../../../../tool/instrumentor/build/libs/kelinci.jar:../../../lib/* Driver.java -d ../../../bin
cd ../../../
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar:./lib/* edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_boot-stateless-auth_safe.
echo "Prepare themis_boot-stateless-auth_safe.."
cd ./themis_boot-stateless-auth_safe/
rm -rf bin
mkdir bin
cd src/main/java/
javac -cp .:../../../../../../tool/instrumentor/build/libs/kelinci.jar:../../../lib/* Driver.java -d ../../../bin
cd ../../../
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar:./lib/* edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_boot-stateless-auth_unsafe.
echo "Prepare themis_boot-stateless-auth_unsafe.."
cd ./themis_boot-stateless-auth_unsafe/
rm -rf bin
mkdir bin
cd src/main/java/
javac -cp .:../../../../../../tool/instrumentor/build/libs/kelinci.jar:../../../lib/* Driver.java -d ../../../bin
cd ../../../
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar:./lib/* edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_boot-stateless-auth_unsafe_corrected.
echo "Prepare themis_boot-stateless-auth_unsafe_corrected.."
cd ./themis_boot-stateless-auth_unsafe_corrected/
rm -rf bin
mkdir bin
cd src/main/java/
javac -cp .:../../../../../../tool/instrumentor/build/libs/kelinci.jar:../../../lib/* Driver.java -d ../../../bin
cd ../../../
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar:./lib/* edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_dynatable_unsafe.
echo "Prepare themis_dynatable_unsafe.."
cd ./themis_dynatable_unsafe/
rm -rf bin
mkdir bin
cd src/main/java/
javac -cp .:../../../../../../tool/instrumentor/build/libs/kelinci.jar:../../../lib/* Driver.java -d ../../../bin
cd ../../../
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar:./lib/* edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_dynatable_unsafe_corrected.
echo "Prepare themis_dynatable_unsafe_corrected.."
cd ./themis_dynatable_unsafe_corrected/
rm -rf bin
mkdir bin
cd src/main/java/
javac -cp .:../../../../../../tool/instrumentor/build/libs/kelinci.jar:../../../lib/* Driver.java -d ../../../bin
cd ../../../
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar:./lib/* edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_jdk_safe.
echo "Prepare themis_jdk_safe.."
cd ./themis_jdk_safe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar MessageDigest_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_jdk_unsafe.
echo "Prepare themis_jdk_unsafe.."
cd ./themis_jdk_unsafe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar MessageDigest_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_jdk_unsafe_corrected.
echo "Prepare themis_jdk_unsafe_corrected.."
cd ./themis_jdk_unsafe_corrected/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar MessageDigest_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_jetty_safe.
echo "Prepare themis_jetty_safe.."
cd ./themis_jetty_safe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar Credential_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_jetty_unsafe.
echo "Prepare themis_jetty_unsafe.."
cd ./themis_jetty_unsafe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar Credential_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_jetty_unsafe_corrected.
echo "Prepare themis_jetty_unsafe_corrected.."
cd ./themis_jetty_unsafe_corrected/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar Credential_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_oacc_unsafe.
echo "Prepare themis_oacc_unsafe.."
cd ./themis_oacc_unsafe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar:../lib/* Driver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar:./lib/* edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_oacc_unsafe_corrected.
echo "Prepare themis_oacc_unsafe_corrected.."
cd ./themis_oacc_unsafe_corrected/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar:../lib/* Driver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar:./lib/* edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_orientdb_safe.
echo "Prepare themis_orientdb_safe.."
cd ./themis_orientdb_safe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar:../lib/* OSecurityManager_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar:./lib/* edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_orientdb_unsafe.
echo "Prepare themis_orientdb_unsafe.."
cd ./themis_orientdb_unsafe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar:../lib/* OSecurityManager_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar:./lib/* edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_orientdb_unsafe_corrected.
echo "Prepare themis_orientdb_unsafe_corrected.."
cd ./themis_orientdb_unsafe_corrected/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar:../lib/* OSecurityManager_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar:./lib/* edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_picketbox_safe.
echo "Prepare themis_picketbox_safe.."
cd ./themis_picketbox_safe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar UsernamePasswordLoginModule_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_picketbox_unsafe.
echo "Prepare themis_picketbox_unsafe.."
cd ./themis_picketbox_unsafe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar UsernamePasswordLoginModule_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_picketbox_unsafe_corrected.
echo "Prepare themis_picketbox_unsafe_corrected.."
cd ./themis_picketbox_unsafe_corrected/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar UsernamePasswordLoginModule_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_spring-security_safe.
echo "Prepare themis_spring-security_safe.."
cd ./themis_spring-security_safe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar PasswordEncoderUtils_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_spring-security_unsafe.
echo "Prepare themis_spring-security_unsafe.."
cd ./themis_spring-security_unsafe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar PasswordEncoderUtils_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_spring-security_unsafe_corrected.
echo "Prepare themis_spring-security_unsafe_corrected.."
cd ./themis_spring-security_unsafe_corrected/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar PasswordEncoderUtils_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_tomcat_safe.
echo "Prepare themis_tomcat_safe.."
cd ./themis_tomcat_safe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar:../lib/* Tomcat_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar:./lib/* edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_tomcat_unsafe.
echo "Prepare themis_tomcat_unsafe.."
cd ./themis_tomcat_unsafe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar:../lib/* Tomcat_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar:./lib/* edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_tomcat_unsafe_corrected.
echo "Prepare themis_tomcat_unsafe_corrected.."
cd ./themis_tomcat_unsafe_corrected/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar:../lib/* Tomcat_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar:./lib/* edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

echo "Done."
