FROM openjdk:19-alpine
RUN addgroup -S spring && adduser -S spring -G spring
EXPOSE 8082
ARG DEPENDENCY=target
ADD ${DEPENDENCY}/*.jar profileservice.jar
ENTRYPOINT ["java","-jar","/profileservice.jar"]