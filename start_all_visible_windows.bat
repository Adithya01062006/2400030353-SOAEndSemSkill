@echo off
echo ========================================================
echo   Starting Microservices Architecture: 2400030353-SOAEndSemSkill
echo ========================================================

cd /d "%~dp0"

echo [1/4] Starting Eureka Server (Port 8761)...
start "Eureka Discovery Server [8761]" cmd /k "cd eureka-server\target && java -jar eureka-server-0.0.1-SNAPSHOT.jar"
timeout /t 10 /nobreak

echo [2/4] Starting Tourist Service - Instance 1 (Port 8081)...
start "Tourist Service - Instance 1 [8081]" cmd /k "cd tourist-service\target && java -jar tourist-service-0.0.1-SNAPSHOT.jar"
timeout /t 6 /nobreak

echo [3/4] Starting Tourist Service - Instance 2 [Load Balanced] (Port 8082)...
start "Tourist Service - Instance 2 [8082]" cmd /k "cd tourist-service\target && java -Dserver.port=8082 -jar tourist-service-0.0.1-SNAPSHOT.jar"
timeout /t 6 /nobreak

echo [4/4] Starting API Gateway (Port 8080)...
start "Spring Cloud API Gateway [8080]" cmd /k "cd api-gateway\target && java -jar api-gateway-0.0.1-SNAPSHOT.jar"
timeout /t 6 /nobreak

echo ========================================================
echo   All Microservices launched successfully!
echo.
echo   Eureka Dashboard      : http://localhost:8761
echo   API Gateway           : http://localhost:8080
echo   Direct Token API      : http://localhost:8080/api/token
echo   Swagger UI            : http://localhost:8081/swagger-ui/index.html
echo ========================================================
pause
