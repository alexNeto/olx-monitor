FROM maven:3.8.6-jdk-11-slim AS build

WORKDIR /olx-monitor

VOLUME $HOME/.m2 /root/.m2

ADD ../../pom.xml /olx-monitor

RUN mvn -B dependency:resolve dependency:resolve-plugins

ADD ../../src /olx-monitor/src

RUN mvn -f /olx-monitor/pom.xml clean package


FROM openjdk:11-jre-slim as runtime

WORKDIR /olx-monitor

COPY --from=build /olx-monitor/target/olx-monitor-*.jar application.jar

ENTRYPOINT java -jar -Dspring.profiles.active=container application.jar

