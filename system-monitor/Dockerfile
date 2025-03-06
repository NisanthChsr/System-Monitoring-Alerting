# Use Maven to build the project
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copy only necessary files
COPY . .

# Build system-monitor module only
RUN mvn clean install -DskipTests -pl system-monitor -am

# Use OpenJDK runtime image for running the app
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/system-monitor/target/system-monitor-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
