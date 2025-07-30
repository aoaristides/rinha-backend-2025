# Stage 1: Build the Spring Boot application
FROM amazoncorretto:24-alpine-jdk AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven/Gradle build files and source code
COPY . .

# Build the application (adjust for Gradle if needed)
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the final image
FROM amazoncorretto:24-alpine-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the port your Spring Boot application listens on
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]