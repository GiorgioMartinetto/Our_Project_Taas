FROM openjdk:19-alpine
RUN addgroup -S spring && adduser -S spring -G spring
EXPOSE 8090
ARG DEPENDENCY=target
ADD ${DEPENDENCY}/*.jar primeservice.jar
ENTRYPOINT ["java","-jar","/primeservice.jar"]