@echo off
setlocal

REM ---- Config ----
set "GROUP_ID=com.external"
set "VERSION=1.0.0"
set "LIB_DIR=lib"

for %%f in (%LIB_DIR%\*.jar) do (
    echo Installing %%f ...
    mvn install:install-file -Dfile="%%f" -DgroupId=%GROUP_ID% -DartifactId=%%~nf -Dversion=%VERSION% -Dpackaging=jar
)

echo All jars installed successfully!
pause
