FROM java:8-jre

ADD ./target/payment.jar /app/
CMD ["java","-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n", "-Xmx200m", "-jar", "app/payment.jar"]

EXPOSE 8007