FROM java:8-jre

ADD ./target/service-discovery.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/service-discovery.jar"]

EXPOSE 8001