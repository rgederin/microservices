FROM java:8-jre

ADD ./target/price-comparator-front.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/price-comparator-front.jar"]

EXPOSE 8005