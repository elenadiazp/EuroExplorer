# Etapa 1: Build con Maven y Java 21
FROM maven:3.9.4-eclipse-temurin-21 as build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Etapa 2: Runtime con JDK 21 Alpine
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/proyecto-0.0.1-SNAPSHOT.jar app.jar
COPY wallet ./wallet

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

