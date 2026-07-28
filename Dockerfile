FROM amazoncorretto:17
EXPOSE 8080
ADD target/SpringBootCrud.jar SpringBootCrud.jar
ENTRYPOINT ["java","-jar","/SpringBootCrud.jar" ]