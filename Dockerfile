FROM openjdk:8-jdk-alpine

ARG JAR_FILE=build/libs/blog-0.2.0-prod.jar
ADD ${JAR_FILE} app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jarw","/app.jar"]