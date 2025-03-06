# Use Maven to build the project
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copy only necessary files
COPY . .

# Build log-storing module only
RUN mvn clean install -DskipTests -pl log-storing -am

# Use OpenJDK runtime image for running the app
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/log-storing/target/log-storing-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
