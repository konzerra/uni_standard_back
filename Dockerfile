# Build stage
FROM openjdk:17-jdk-slim AS build
WORKDIR /app
COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
RUN apt-get update && apt-get install -y ca-certificates

# Production stage
FROM openjdk:17-jdk-slim AS production
WORKDIR /app
COPY --from=build /app/target/webserver.jar .

# Expose port 8080
EXPOSE 8080
EXPOSE 587
CMD ["java", "-jar", "webserver.jar"]
