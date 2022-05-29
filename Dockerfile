#
# Package stage
#
FROM openjdk:11-jre-slim

EXPOSE 8080

ADD /target/worldwide-windsurfers-weather-service-0.0.1-SNAPSHOT.jar worldwide-windsurfers-weather-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","worldwide-windsurfers-weather-service-0.0.1-SNAPSHOT.jar"]
