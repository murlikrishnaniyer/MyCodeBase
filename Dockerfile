FROM openjdk:8
EXPOSE 8080
ADD target/SpringJpaCrud.jar SpringJpaCrud.jar
ENTRYPOINT["java","-jar","/SpringJpaCrud.jar" ]