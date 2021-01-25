FROM java:8-jre
MAINTAINER Arturo Velázquez <arturovelazquez94@gmail.com>

ADD ./target/config.jar /app/
CMD ["java","-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n", "-Xmx200m", "-jar", "app/config.jar"]

EXPOSE 8002