FROM openjdk:14-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} server.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/server.jar"]