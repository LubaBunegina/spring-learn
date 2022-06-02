FROM openjdk:11-jre-slim
COPY /target/student-service-0.0.1.jar /app/app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/app.jar"]