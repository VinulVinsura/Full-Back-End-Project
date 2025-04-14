# Use an official OpenJDK 17 runtime as a parent image
FROM eclipse-temurin:17-jdk-alpine


# Set the working directory inside the container
WORKDIR /opt

ENV PORT 8080

# Expose port (adjust according to your app)
EXPOSE 8080

# Copy the JAR file from the target folder into the container
COPY target/*.jar  /opt/app.jar


# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

