## prepare.sh
#####################################
# chmod +x prepare.sh
# ./prepare.sh
#

trap "exit" INT

# Prepare apache_ftpserver_stringutils_unsafe.
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

# Prepare blazer_passwordEq_unsafe.
echo "Prepare blazer_passwordEq_unsafe_corrected.."
cd ./blazer_passwordEq_unsafe_corrected/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar User_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare github_authmreloaded_unsafe.
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

# Prepare themis_dynatable_unsafe.
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

# Prepare themis_jdk_unsafe.
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

# Prepare themis_pac4j_safe.
echo "Prepare themis_pac4j_safe.."
cd ./themis_pac4j_safe/
rm -rf bin
mkdir bin
cd src/main/java/
javac -cp .:../../../../../../tool/instrumentor/build/libs/kelinci.jar:../../../lib/* Driver.java -d ../../../bin
cd ../../../
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar:./lib/* edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_pac4j_unsafe.
echo "Prepare themis_pac4j_unsafe.."
cd ./themis_pac4j_unsafe/
rm -rf bin
mkdir bin
cd src/main/java/
javac -cp .:../../../../../../tool/instrumentor/build/libs/kelinci.jar:../../../lib/* Driver.java -d ../../../bin
cd ../../../
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar:./lib/* edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare themis_pac4j_unsafe.
echo "Prepare themis_pac4j_unsafe_corrected.."
cd ./themis_pac4j_unsafe_corrected/
rm -rf bin
mkdir bin
cd src/main/java/
javac -cp .:../../../../../../tool/instrumentor/build/libs/kelinci.jar:../../../lib/* Driver.java -d ../../../bin
cd ../../../
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar:./lib/* edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
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

# Prepare themis_spring-security_unsafe.
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

echo "Done."
