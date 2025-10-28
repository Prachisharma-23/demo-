# Use official Maven image to build the app
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build the jar
COPY src ./src
RUN mvn clean package -DskipTests

# Use lightweight JDK image for running
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy built jar from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
