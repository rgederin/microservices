FROM java:8-jre

ADD ./target/product-catalogue.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/product-catalogue.jar"]

EXPOSE 8002