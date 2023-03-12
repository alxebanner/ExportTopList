@echo off
echo.
echo.
 
cd %~dp0
 
set JAVA_OPTS=-Xms256m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m
 
java -jar %JAVA_OPTS%  toTopList-1.0-SNAPSHOT.jar -Dspring.config.location=\application.yml
 
cd bin
pause;
