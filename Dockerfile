# Image containing Java runtime
FROM openjdk:13-jdk-alpine

# Maintainer info
LABEL maintainer="Kamil Celejewski"

# Application jar file
ARG JAR_FILE=target/*.jar

# Add application jar to the container
COPY ${JAR_FILE} app.jar

# Run jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]