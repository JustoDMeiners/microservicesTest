FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/*.jar cliente-microservice.jar
ENTRYPOINT ["java","-jar","/cliente-microservice.jar"]