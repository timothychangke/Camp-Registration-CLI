#To run project on Mac

#!/bin/bash

# Compile the project
javac -d out src/Boundary/Mains/Main.java

# Create the manifest file
echo "Main-Class: src.Boundary.Mains.Main" > Manifest.txt

# Create the JAR file
jar cfm YourExecutable.jar Manifest.txt -C out .

# Run the executable JAR
java -jar YourExecutable.jar