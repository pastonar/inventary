FROM openjdk:17-jdk

COPY target/Inventary.jar app_inventary.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_inventary.jar"]
