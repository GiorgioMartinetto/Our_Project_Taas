FROM openjdk:19-jdk-alpine
EXPOSE 8080
ARG DEPENDENCY=target
COPY ${DEPENDENCY}/*.jar apigateway.jar
ENTRYPOINT ["java", "-jar", "/apigateway.jar"]