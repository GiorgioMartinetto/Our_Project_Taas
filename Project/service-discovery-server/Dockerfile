FROM openjdk:19-jdk-alpine
EXPOSE 8761
ARG DEPENDENCY=target
COPY ${DEPENDENCY}/*.jar servicediscoveryserver.jar
ENTRYPOINT ["java", "-jar", "/servicediscoveryserver.jar"]