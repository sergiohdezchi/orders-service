Orders API 

Este proyecto es una API REST para la gestión de órdenes, desarrollada con Java y Spring Boot, y diseñada para comunicar eventos a
través de Kafka. Todos los servicios se orquestan con Docker para facilitar su ejecución y despliegue. 
🧰 Requisitos 
Asegúrate de tener lo siguiente instalado: 
Docker
Docker Compose
git para clonar el repositorio 
🚀 Ejecución rápida con Docker 
1. Clona el repositorio 
git clone https://github.com/sergiohdezchi/orders-service.git
cd orders-api

2. Construye y ejecuta los contenedores 
docker-compose up --build

Esto levantará los siguientes servicios: 
Kafka (localhost:9092)
Zookeeper (necesario para Kafka)
MongoDB (localhost:27018)
Orders API (localhost:8081) 

3. Verifica que los contenedores estén corriendo 
docker-compose ps

Debes ver algo similar a: 
Name Command State Ports
-----------------------------------------------------------------------------
orders-api-1 java -jar /app/orders-api.jar Up 0.0.0.0:8080->8080/tcp
kafka-1 ... Up 0.0.0.0:9092->9092/tcp
zookeeper-1 ... Up 2181/tcp

4. API disponible 
Puedes acceder a la API desde: 
http://localhost:8081

Usa Postman, Insomnia o curl para interactuar con los endpoints. 
5. Envío de eventos a Kafka 
La API publica mensajes al topic orders al crear nuevas órdenes. Verifica el log del contenedor para confirmar que los mensajes se
están enviando: 
docker-compose logs orders-api

6. Detener los contenedores 
docker-compose down
Esto detendrá y eliminará los contenedores, redes y volúmenes creados por Docker. 


