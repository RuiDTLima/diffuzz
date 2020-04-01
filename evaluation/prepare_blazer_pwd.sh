## prepare.sh
#####################################
# chmod +x prepare.sh
# ./prepare.sh
#

trap "exit" INT

# Prepare blazer_passwordEq_safe.
echo "Prepare blazer_passwordEq_safe.."
cd ./blazer_passwordEq_safe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../tool/instrumentor/build/libs/kelinci.jar User_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare blazer_passwordEq_unsafe.
echo "Prepare blazer_passwordEq_unsafe.."
cd ./blazer_passwordEq_unsafe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../tool/instrumentor/build/libs/kelinci.jar User_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

echo "Done."
