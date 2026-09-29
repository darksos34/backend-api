FROM eclipse-temurin:25-jre

WORKDIR /backend-api

COPY target/backend-api-*.jar app.jar

ENV SERVER_PORT=8081

EXPOSE 8081

ENTRYPOINT ["sh","-c","java -jar app.jar --server.port=${SERVER_PORT}"]
