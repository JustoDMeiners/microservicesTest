FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/*.jar cuenta-service.jar
ENTRYPOINT ["java","-jar","/cuenta-service.jar"]