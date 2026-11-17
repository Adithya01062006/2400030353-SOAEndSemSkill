# 2400030353-SOAEndSemSkill

## About the Project
This is a microservices project for SOA Lab Skill Exam. It contains:
- Eureka Server for service discovery
- Tourist Service with JWT token generation and Swagger UI
- API Gateway for routing and load balancing

## Technologies Used
- Java 21
- Spring Boot 3
- Spring Cloud (Eureka & Gateway)
- Spring Security & JWT
- H2 Database
- Swagger UI (SpringDoc OpenAPI)

## How to Run the Project
1. Run `start_all_visible_windows.bat` to start all services in separate windows.
2. Open Eureka Dashboard: http://localhost:8761
3. Access API via Gateway: http://localhost:8080/api/token
4. Open Swagger UI: http://localhost:8081/swagger-ui/index.html
5. To stop all services, run `stop_all.bat`.
