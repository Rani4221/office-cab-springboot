FROM openjdk:11

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container at the defined working directory
COPY target/spring-officecab-docker.jar /app/app.jar

# Expose the port the app runs on
EXPOSE 8083

# Specify the command to run on container start
CMD ["java", "-jar", "app.jar"]