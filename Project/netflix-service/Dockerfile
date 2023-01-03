FROM openjdk:19-alpine
RUN addgroup -S spring && adduser -S spring -G spring
EXPOSE 8091
ARG DEPENDENCY=target
ADD ${DEPENDENCY}/*.jar netflixservice.jar
ENTRYPOINT ["java","-jar","/netflixservice.jar"]