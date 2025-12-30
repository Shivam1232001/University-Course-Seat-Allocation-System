# Use lightweight Java image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy JAR file
COPY target/seat-allocation-service-0.0.1-SNAPSHOT.jar app.jar

# Expose service port
EXPOSE 8082

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]
