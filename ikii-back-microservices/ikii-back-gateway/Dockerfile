FROM java:8-jre
MAINTAINER Arturo Velázquez <arturovelazquez94@gmail.com>

ADD ./target/gateway.jar /app/
CMD ["java", "-Xmx1024m", "-jar", "app/gateway.jar"]

EXPOSE 4000