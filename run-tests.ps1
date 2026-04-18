# Compile main source
javac -cp "test-lib/junit-platform-console-standalone-1.13.0-M3.jar" `
-d target/classes `
src\CMSC230\*.java

if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }

# Compile tests
javac -cp "target/classes;test-lib/junit-platform-console-standalone-1.13.0-M3.jar" `
-d target/test-classes `
test\CMSC230\*.java

if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }

# Run tests
java -jar test-lib/junit-platform-console-standalone-1.13.0-M3.jar `
--class-path "target/classes;target/test-classes" `
--scan-class-path