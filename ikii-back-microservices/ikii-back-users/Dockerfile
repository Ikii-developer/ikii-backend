FROM java:8-jre
MAINTAINER Arturo Velázquez <arturovelazquez94@gmail.com>

ADD ./target/users.jar /app/
CMD ["java", "-Xmx200m", "-jar", "app/users.jar"]

EXPOSE 8002