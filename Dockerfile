FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy all project files
COPY . .

# Build jar inside container
RUN ./mvnw clean package -DskipTests

# Run the jar
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]