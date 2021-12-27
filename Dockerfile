FROM openjdk:11-jre-slim

ARG JAR_FILE=./target/*.jar

COPY ${JAR_FILE} application.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=container", "/application.jar"]