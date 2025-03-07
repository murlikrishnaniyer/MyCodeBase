FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8080
ADD target/SpringJpaCrud.jar SpringJpaCrud.jar
ENTRYPOINT["java","-jar","/SpringJpaCrud.jar" ]