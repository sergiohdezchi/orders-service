<div align="center">
  <img src="logo.svg" alt="ECommerceHub Logo" width="500">
  
  # ECommerceHub - Order Management System
  
  ![Java](https://img.shields.io/badge/Java-23-orange?style=flat-square&logo=openjdk)
  ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-brightgreen?style=flat-square&logo=spring)
  ![MongoDB](https://img.shields.io/badge/MongoDB-6.0-green?style=flat-square&logo=mongodb)
  ![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-7.4.0-black?style=flat-square&logo=apache-kafka)
  ![Docker](https://img.shields.io/badge/Docker-Compose-blue?style=flat-square&logo=docker)
</div>

## 📋 Overview

**ECommerceHub** is a robust and scalable order management microservice built with modern enterprise-grade technologies. This RESTful API provides comprehensive order processing capabilities with real-time event streaming, secure authentication, and horizontal scalability.

### 🏗️ Architecture

- **Microservice Architecture**: Domain-driven design with clear separation of concerns
- **Event-Driven Communication**: Asynchronous messaging with Apache Kafka
- **Secure Authentication**: JWT-based authentication with Spring Security
- **Document Database**: MongoDB for flexible and scalable data persistence
- **Containerization**: Docker & Docker Compose for consistent deployments

## 🚀 Key Features

- ✅ **User Management**: Registration, authentication, and JWT token management
- ✅ **Product Catalog**: Comprehensive product management with pagination
- ✅ **Order Processing**: Complete order lifecycle management with validation
- ✅ **Event Streaming**: Real-time order events via Apache Kafka
- ✅ **Security**: Role-based access control and secure endpoints
- ✅ **API Documentation**: RESTful API with proper HTTP status codes
- ✅ **Error Handling**: Comprehensive exception handling and validation
- ✅ **Scalability**: Horizontally scalable microservice architecture

## 🛠️ Technology Stack

| Component | Technology | Version |
|-----------|------------|---------|
| **Runtime** | Java OpenJDK | 23 |
| **Framework** | Spring Boot | 3.4.5 |
| **Security** | Spring Security + JWT | Latest |
| **Database** | MongoDB | 6.0 |
| **Messaging** | Apache Kafka | 7.4.0 |
| **Build Tool** | Maven | 3.9+ |
| **Containerization** | Docker Compose | 3.8 |

## 📦 Prerequisites

Before running the application, ensure you have the following installed:

- **Docker** (20.10+)
- **Docker Compose** (2.0+)
- **Git** (for cloning the repository)

## 🚀 Quick Start

### 1. Clone the Repository

```bash
git clone <repository-url>
cd orders-api
```

### 2. Environment Setup

The application runs entirely in Docker containers. All dependencies (MongoDB, Kafka, Zookeeper) are automatically configured.

### 3. Start the Application

```bash
docker compose up --build
```

This command will:
- Build the Spring Boot application
- Start MongoDB database
- Initialize Kafka and Zookeeper
- Launch the API server on port 8081

### 4. Verify Installation

Access the application at: `http://localhost:8081`

## 📚 API Documentation

### Base URL
```
http://localhost:8081
```

### Authentication Endpoints

#### User Registration
```http
POST /auth/sign-up
Content-Type: application/json

{
    "email": "user@example.com",
    "password": "securePassword123"
}
```

#### User Login
```http
POST /auth/sign-in
Content-Type: application/json

{
    "email": "user@example.com",
    "password": "securePassword123"
}
```

### Product Endpoints

#### Get Products (Paginated)
```http
GET /products/page?page=0&size=5
Authorization: Bearer <jwt-token>
```

### Order Endpoints

#### Create Order
```http
POST /orders
Authorization: Bearer <jwt-token>
Content-Type: application/json

{
    "items": [
        {
            "id": "product-id",
            "quantity": 2
        }
    ]
}
```

#### Get Orders (Paginated)
```http
GET /orders/page?page=0&size=5&userId=<user-id>
Authorization: Bearer <jwt-token>
```

## 🔧 Development

### Local Development Setup

For development without Docker:

1. **Start Required Services**
   ```bash
   # Start only MongoDB and Kafka
   docker compose up mongo kafka zookeeper -d
   ```

2. **Run Application**
   ```bash
   ./mvnw spring-boot:run -Dspring.profiles.active=dev
   ```

### Build Application

```bash
mvn clean package -Dspring.profiles.active=default
```

### Testing

```bash
mvn test
```

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/com/helier/orders/orders_api/
│   │   ├── config/           # Configuration classes
│   │   ├── controller/       # REST controllers
│   │   ├── dto/             # Data transfer objects
│   │   ├── exception/       # Exception handling
│   │   ├── kafka/           # Kafka producers/consumers
│   │   ├── model/           # Domain entities
│   │   ├── repository/      # Data access layer
│   │   └── service/         # Business logic
│   └── resources/
│       └── application.properties
└── test/                    # Test classes
```

## 🌐 Environment Configuration

### Production Environment

Update `docker-compose.yml` for production:

```yaml
environment:
  SPRING_PROFILES_ACTIVE: prod
  SPRING_DATA_MONGODB_URI: mongodb://mongo:27017/orders-prod
  JWT_SECRET: <your-production-secret>
```

### Security Considerations

- Change default JWT secret in production
- Use environment variables for sensitive data
- Enable SSL/TLS for production deployments
- Implement proper network security groups

## 🚦 Event Streaming

The application publishes order events to Kafka topics:

- **Topic**: `orders`
- **Event Types**: Order Created, Order Updated, Order Cancelled
- **Consumer Group**: `order-group`

## 📊 Monitoring & Health Checks

Spring Boot Actuator endpoints available:
- `/actuator/health` - Application health status
- `/actuator/info` - Application information
- `/actuator/metrics` - Application metrics

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 📸 API Screenshots

### Authentication Flow
![Authentication](images/01.png)

### User Registration
![Registration](images/02.png)

### JWT Token Authentication
![JWT Authentication](images/03.png)

### Product Catalog
![Product Catalog](images/06.png)

### Order Creation
![Order Creation](images/08.png)

### Kafka Event Processing
![Kafka Events](images/09.png)

### Order Management
![Order Management](images/10.png)

