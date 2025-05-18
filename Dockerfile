# Etapa 1: Build con Maven
FROM maven:3.9.0-eclipse-temurin-21 as build

WORKDIR /app

# Copia pom y código fuente
COPY pom.xml .
COPY src ./src

# Construye el .jar (sin ejecutar tests para acelerar)
RUN mvn clean package -DskipTests

# Etapa 2: Imagen runtime con JDK para ejecutar la app
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copia el .jar compilado desde la etapa anterior
COPY --from=build /app/target/proyecto-0.0.1-SNAPSHOT.jar app.jar

# Copia la carpeta wallet completa
COPY wallet ./wallet

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
