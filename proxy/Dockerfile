FROM openjdk:14-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} proxy.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/proxy.jar"]