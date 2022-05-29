#
# Package stage
#
FROM openjdk:11-jre-slim

EXPOSE 8080

COPY /target/worldwide-windsurfers-weather-service-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java","-jar","worldwide-windsurfers-weather-service-0.0.1-SNAPSHOT.jar"]
