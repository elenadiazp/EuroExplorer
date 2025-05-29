# Etapa 1: utiliza imagen maven con JDK para compilar el proyecto
FROM maven:3.9.4-eclipse-temurin-21 as build
#indicar el directorio de tarbajo  con los archivos necesarios
WORKDIR /app
#copiar los archivos necesarios
COPY pom.xml .
COPY src ./src
#complia y genera .jar, y se omite los test para que sea más rápido
RUN mvn clean package -DskipTests

# Etapa 2: imagen ligera solo para ejecutar
FROM eclipse-temurin:21-jdk-alpine
#define el directorio de trabajo
WORKDIR /app
#copia el .jar y el wallet
COPY --from=build /app/target/proyecto-0.0.1-SNAPSHOT.jar app.jar
COPY wallet ./wallet
#puerto donde se va a ejecutar la aplicación
EXPOSE 8080
# inicia la aplicacion
ENTRYPOINT ["java", "-jar", "app.jar"]

