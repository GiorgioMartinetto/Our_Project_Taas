FROM openjdk:19-alpine
RUN addgroup -S spring && adduser -S spring -G spring
EXPOSE 8081
ARG DEPENDENCY=target
ADD ${DEPENDENCY}/*.jar userservice.jar
ENTRYPOINT ["java","-jar","/userservice.jar"]