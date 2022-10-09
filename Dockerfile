FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY /target/demo-0.0.1-SNAPSHOT.jar .

EXPOSE 5432

ENTRYPOINT ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]