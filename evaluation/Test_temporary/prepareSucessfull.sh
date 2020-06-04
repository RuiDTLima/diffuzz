## prepare.sh
#####################################
# chmod +x prepare.sh
# ./prepare.sh
#

trap "exit" INT

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

echo "Done."