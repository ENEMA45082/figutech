@ECHO OFF
SETLOCAL

SET WRAPPER_DIR=%~dp0
SET PROJECT_DIR=%WRAPPER_DIR%
IF NOT "%MAVEN_PROJECTBASEDIR%"=="" SET PROJECT_DIR=%MAVEN_PROJECTBASEDIR%

SET MAVEN_WRAPPER_PROPERTIES=%PROJECT_DIR%\.mvn\wrapper\maven-wrapper.properties
SET MAVEN_WRAPPER_JAR=%PROJECT_DIR%\.mvn\wrapper\maven-wrapper.jar

IF NOT EXIST "%MAVEN_WRAPPER_JAR%" (
  FOR /F "tokens=2 delims==" %%G IN ('findstr /R /C:"^wrapperUrl=" "%MAVEN_WRAPPER_PROPERTIES%"') DO SET WRAPPER_URL=%%G
  powershell -NoProfile -Command "Invoke-WebRequest -Uri '%WRAPPER_URL%' -OutFile '%MAVEN_WRAPPER_JAR%'" || GOTO :error
)

java -cp "%MAVEN_WRAPPER_JAR%" -Dmaven.multiModuleProjectDirectory="%PROJECT_DIR%" org.apache.maven.wrapper.MavenWrapperMain %*
IF %ERRORLEVEL% NEQ 0 GOTO :error

GOTO :eof

:error
EXIT /B 1
