FROM java:8-jre
MAINTAINER Arturo Velázquez <arturovelazquez94@gmail.com>

ADD ./target/transactions.jar /app/
CMD ["java","-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n", "-Xmx200m", "-jar", "app/transactions.jar"]

EXPOSE 8001