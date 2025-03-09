FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8080
ADD target/SpringBootApplicationOnKubernetes.jar SpringBootApplicationOnKubernetes.jar
ENTRYPOINT ["java","-jar","/SpringBootApplicationOnKubernetes.jar" ]