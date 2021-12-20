FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} tipo-cambio-rest-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/tipo-cambio-rest-0.0.1.jar"]