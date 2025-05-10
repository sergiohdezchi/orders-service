## Orders API 

Este proyecto es una API REST para la gestión de órdenes, desarrollada con Java y Spring Boot, y diseñada para comunicar eventos a
través de Kafka. Todos los servicios se orquestan con Docker para facilitar su ejecución y despliegue. 

## 🧰 Requisitos 

Docker
Docker Compose
git para clonar el repositorio 


## 🚀 Ejecución rápida con Docker 
1. Clona el repositorio 
```bash
git clone https://github.com/sergiohdezchi/orders-service.git
cd orders-service
```
2. 🐳 Levantar la aplicación con Docker

```bash
docker compose up --build
```

## API

### Apuntar a la API
```bash
http://localhost:8081
```
![image.png](images/01.png)

### Registrar Usuario en el Endpoint
```bash
POST {{base_url}}/auth/sign-up
```

![image.png](images/02.png)

### Login con el usuario registrado
```bash
POST {{base_url}}/auth/sign-up

{
    "email": "sergio.hernandez+9@gmail.com",
    "password": "12345678"
}
```
![image.png](images/03.png)

### Copiar token

![image.png](images/04.png)

### Usar token como Bearer Token

![image.png](images/05.png)

### Usar Endpoint para obtener la lista de productos

```bash
GET {{base_url}}/products/page?page=0
```
En query params puedes usar page para el numero de pagina

![image.png](images/06.png)

### Copiar el ID del producto
![image.png](images/07.png)

### Crear Orden
```bash
POTS {{base_url}}/orders

{
    "items": [
        {
            "id": "681e8391d06ba3558119b0c8",
            "quantity": 2
        }
    ]
}
```
![image.png](images/08.png)


### Rervisar el mensaje que imprime el consumer como prueba

![image.png](images/09.png)

### Listar Ordenes
Puedes usar page para navegar entre paginas y userId para filtrar por usuario

```bash
GET {{base_url}}/orders/page?page=0&userId=681e8449d06ba3558119b0ca
```

![image.png](images/10.png)

![image.png](images/11.png)

### Recompilar  el proyecto en caso de ser necesario

```bash
mvn clean package -Dspring.profiles.active=default
```

