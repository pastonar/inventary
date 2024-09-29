FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/Inventary.jar
COPY Inventary.jar app_inventary.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_inventary.jar"]
