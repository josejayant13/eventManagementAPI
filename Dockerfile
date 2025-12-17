#maven:3.9.6-eclipse-temurin-21 is existing docker image.
#It contains Maven 3.9.6, Java 21 and Linux base (slice of Linux OS)
#below command will start s build stage named as "build". This will build the sprinboot
# app in the docker environment
FROM maven:3.9.6-eclipse-temurin-21 AS build
# set working dir in path /app
WORKDIR /app
# copy project's pom.xml to working dir /app
COPY pom.xml .
# copy projects src folder to path /app/src
COPY src ./src
# start fresh, build the app, create the jar file and skip tests.
RUN mvn clean package -DskipTests

#eclipse-temurin:21-jre-alpine is an existing docker image.
#It contains Java 21 and Alpine Linux(very small linux base)
#Final stage to run the application
FROM eclipse-temurin:21-jre-alpine
# Copy the jar created during build stage in path /app/target/ to app.jar
COPY --from=build /app/target/*.jar app.jar
# Export port 8080
EXPOSE 8080
#When container starts run this command java -jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]