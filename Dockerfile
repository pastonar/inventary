FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/inventary.jar
COPY inventary.jar app_inventary.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_inventary.jar"]