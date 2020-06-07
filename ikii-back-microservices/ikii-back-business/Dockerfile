FROM openjdk:8-alpine

VOLUME /tmp

COPY ./target/*.jar /app/app.jar
CMD ["java", "-Xmx200m", "-jar", "app/app.jar"]