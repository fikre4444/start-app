@echo off
REM Get the current user's desktop directory
set DESKTOP=%USERPROFILE%\Desktop

REM Change directory to the desktop location
cd "%DESKTOP%"

REM Start the Spring Boot app
start java -jar greet.jar

REM Define the URL to check
set URL=http://localhost:8080/api/message/say-hello

REM Wait for the application to start
echo Waiting for the application to start...
:check
REM Send a request to the endpoint and check the response
for /f "tokens=* delims=" %%A in ('curl --silent %URL%') do (
    if "%%A"=="hello" (
        echo Application has started!
        goto launch_browser
    ) else (
        echo Application is not ready yet!
    )
)
REM Wait for 5 seconds before checking again
timeout /t 5 /nobreak >nul
goto check

:launch_browser
REM Open the Spring Boot application in the default web browser
start http://localhost:8080

REM Exit the script
exit
