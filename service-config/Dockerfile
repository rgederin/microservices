# The smallest Docker image with OracleJDK 8 (167MB)
FROM frolvlad/alpine-oraclejdk8:slim

# add bash and coreutils
RUN apk add --no-cache bash coreutils

MAINTAINER altfatterz@gmail.com

# We added a VOLUME pointing to "/tmp" because that is where a Spring Boot application creates working directories for
# Tomcat by default. The effect is to create a temporary file on your host under "/var/lib/docker" and link it to the
# container under "/tmp". This step is optional for the simple app that we wrote here, but can be necessary for other
# Spring Boot applications if they need to actually write in the filesystem.
VOLUME /tmp

# The project JAR file is ADDed to the container as "app.jar"
ADD target/service-config.jar app.jar

# script files related to check if the datastore is “ready”
ADD scripts/wait-for-it.sh wait-for-it.sh
ADD scripts/start.sh start.sh

EXPOSE 8010

# You can use a RUN command to "touch" the jar file so that it has a file modification time
# (Docker creates all container files in an "unmodified" state by default)
# This actually isn’t important for the simple app that we wrote, but any static content (e.g. "index.html")
# would require the file to have a modification time.
RUN ["chmod", "+x", "/wait-for-it.sh"]
RUN ["chmod", "+x", "/start.sh"]
RUN bash -c 'touch /app.jar'

CMD ["./start.sh"]
