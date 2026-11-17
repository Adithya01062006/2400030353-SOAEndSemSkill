@echo off
echo ========================================================
echo   Stopping All Running Microservices...
echo ========================================================

powershell -Command "Get-Process -Name java -ErrorAction SilentlyContinue | Stop-Process -Force -ErrorAction SilentlyContinue"

echo.
echo   All Microservice processes stopped successfully!
echo ========================================================
pause
