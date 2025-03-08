FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8080
ADD target/SpringBootCrud.jar SpringBootCrud.jar
ENTRYPOINT["java","-jar","/SpringBootCrud.jar" ]