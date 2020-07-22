FROM openjdk:14-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} endconf.jar
EXPOSE 8070
ENTRYPOINT ["java","-jar","/endconf.jar"]