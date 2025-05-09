FROM eclipse-temurin:23-jdk as runtime

ARG JAR_FILE=target/orders-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} orders_api.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "orders_api.jar"]