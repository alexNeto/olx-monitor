FROM maven:3.8.6-jdk-11-slim AS build

WORKDIR /olx-monitor

RUN mvn -f /olx-monitor/pom.xml clean package