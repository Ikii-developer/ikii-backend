FROM java:8-jre
MAINTAINER Arturo Velázquez <arturovelazquez94@gmail.com>

ADD ./target/registry.jar /app/
CMD ["java", "-Xmx200m", "-jar", "app/registry.jar"]

EXPOSE 8761