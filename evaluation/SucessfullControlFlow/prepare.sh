## prepare.sh
#####################################
# chmod +x prepare.sh
# ./prepare.sh
#

trap "exit" INT

# Prepare blazer_array_safe.
echo "Prepare blazer_array_safe.."
cd ./blazer_array_safe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar MoreSanity_Array_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare blazer_array_unsafe.
echo "Prepare blazer_array_unsafe.."
cd ./blazer_array_unsafe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar MoreSanity_Array_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare blazer_gpt14_safe.
echo "Prepare blazer_gpt14_safe.."
cd ./blazer_gpt14_safe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar GPT14_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare blazer_gpt14_unsafe.
echo "Prepare blazer_gpt14_unsafe.."
cd ./blazer_gpt14_unsafe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar GPT14_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare blazer_k96_safe.
echo "Prepare blazer_k96_safe.."
cd ./blazer_k96_safe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar K96_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare blazer_k96_unsafe.
echo "Prepare blazer_k96_unsafe.."
cd ./blazer_k96_unsafe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar K96_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare blazer_modpow1_safe.
echo "Prepare blazer_modpow1_safe.."
cd ./blazer_modpow1_safe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar ModPow1_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare blazer_modpow1_unsafe.
echo "Prepare blazer_modpow1_unsafe.."
cd ./blazer_modpow1_unsafe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar ModPow1_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare blazer_straightline_safe.
echo "Prepare blazer_straightline_safe.."
cd ./blazer_straightline_safe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar Sanity_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare blazer_straightline_unsafe.
echo "Prepare blazer_straightline_unsafe.."
cd ./blazer_straightline_unsafe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar Sanity_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare blazer_unixlogin_safe.
echo "Prepare blazer_unixlogin_safe.."
cd ./blazer_unixlogin_safe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar Timing_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare blazer_unixlogin_unsafe.
echo "Prepare blazer_unixlogin_unsafe.."
cd ./blazer_unixlogin_unsafe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar Timing_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

# Prepare stac_ibasys_unsafe.
echo "Prepare stac_ibasys_unsafe.."
cd ./stac_ibasys_unsafe/
rm -rf bin
mkdir bin
cd src
javac -cp .:../../../../tool/instrumentor/build/libs/kelinci.jar ImageMatcher_FuzzDriver.java -d ../bin
cd ..
rm -rf bin-instr
java -cp ../../../tool/instrumentor/build/libs/kelinci.jar edu.cmu.sv.kelinci.instrumentor.Instrumentor -mode LABELS -i ./bin/ -o ./bin-instr -skipmain
cd ..
echo ""

echo "Done."
