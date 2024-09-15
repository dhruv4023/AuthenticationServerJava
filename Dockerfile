# Use the official OpenJDK as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml /app/pom.xml
RUN apk add --no-cache maven && mvn dependency:go-offline

# Copy the source code (excluding the target directory)
COPY . /app
 
# Expose port (same as in application.properties)
EXPOSE 8080

# Run Spring Boot in development mode (watch for changes)
CMD ["mvn", "spring-boot:run"]
