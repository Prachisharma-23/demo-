# Use official OpenJDK 17 image
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy built jar file from target folder
COPY target/*.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
