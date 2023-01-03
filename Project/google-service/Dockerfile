FROM openjdk:19-alpine
RUN addgroup -S spring && adduser -S spring -G spring
EXPOSE 9191
ARG DEPENDENCY=target
ADD ${DEPENDENCY}/*.jar googleservice.jar
ENTRYPOINT ["java","-jar","/googleservice.jar"]