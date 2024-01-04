@echo off

REM Compile the project
javac -d out src/Boundary/Mains/Main.java

REM Create the manifest file
echo Main-Class: src.Boundary.Mains.Main > Manifest.txt

REM Create the JAR file
jar cfm YourExecutable.jar Manifest.txt -C out .

REM Run the executable JAR
java -jar YourExecutable.jar

REM Pause to keep the command prompt open for viewing any messages
pause