# 2 stage java jar application
# Stage 1: Build the application
FROM maven:3.8.8-eclipse-temurin-17-alpine AS build

# Set the working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml /app/pom.xml
RUN mvn dependency:go-offline

# Copy the source code and build the WAR/JAR file
COPY . /app
RUN mvn package -DskipTests

# Verify the .jar file exists in the target directory (optional, for debugging)
RUN ls -al /app/target

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-alpine

# Set the path for the JAR file
ARG JAR_FILE=/app/target/*.jar

# Copy the built JAR file from the build stage
COPY --from=build ${JAR_FILE} app.jar

# Expose the port that the application will run on
EXPOSE 8080

# Specify the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]




# # Use Maven and JDK in a single stage -----------------------------------------------
# FROM maven:3.8.8-eclipse-temurin-17-alpine

# # Set the working directory
# WORKDIR /app

# # Copy pom.xml and download dependencies
# COPY pom.xml /app/pom.xml
# RUN mvn dependency:go-offline

# # Copy the entire source code and build the WAR/JAR file
# COPY . /app
# RUN mvn package -DskipTests

# # Verify the JAR file exists in the target directory (optional, for debugging)
# RUN ls -al /app/target

# # Expose the port the application will run on
# EXPOSE 8080

# # Specify the command to run the application (assuming JAR is created in /target)
# CMD ["java", "-jar", "/app/target/your-app.jar"]
