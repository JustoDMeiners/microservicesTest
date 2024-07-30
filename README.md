# microservicesTest 
Microservices with RabbitMQ


 Requisitos:
- Java 17 o superior
- Maven 3.6.3 o superior (version 3.8.1 recomendada)
- Docker Compose
- Git

# Pasos para levantar el proyecto

-Clonar repositorio en maquina local.

-Ingresar a la carpeta demoTestMicroservices

-Ingresar a la carpeta cuenta-service y ejecutar "mvn clean package"

-Ingresar a la carpeta cliente-microservice y ejecutar "mvn clean package"

-Volver a la carpeta raiz del proyecto demoTestMicroservices y ejecutar "docker-compose up --build" para levantar contenedores con los distintos servicios que requiere el proyecto.

-Importar colleciones de Postman que se encuentra dentro de la carpeta postman en la raiz del proyecto.





  




