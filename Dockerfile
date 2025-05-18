# Imagen base con JDK 21 (coincide con tu pom.xml)
FROM eclipse-temurin:21-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el .jar generado por Maven
COPY target/proyecto-0.0.1-SNAPSHOT.jar app.jar

# Copia la carpeta wallet completa con Wallet_EuroExplorer dentro
COPY wallet ./wallet

# Expone el puerto 8080
EXPOSE 8080

# Ejecuta la app
ENTRYPOINT ["java", "-jar", "app.jar"]
