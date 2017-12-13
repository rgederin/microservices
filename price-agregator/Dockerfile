FROM java:8-jre

ADD ./target/price-agregator.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/price-agregator.jar"]

EXPOSE 8003