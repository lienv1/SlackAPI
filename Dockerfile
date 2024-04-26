# Use a base image with Java and Spring Boot dependencies
FROM openjdk:17-alpine

# Set the working directory
WORKDIR /app

# Copy the backend code to the container
COPY target/SlackAPI-0.0.1-SNAPSHOT.jar /app/SlackAPI-0.0.1-SNAPSHOT.jar

# Build the backend application
# RUN ./gradlew build

# Expose the port on which the backend will listen
EXPOSE 8080

# Specify the command to run the backend application
CMD ["java", "-jar", "SlackAPI-0.0.1-SNAPSHOT.jar"]