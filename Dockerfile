# Get tomcat image with java 11
FROM tomcat:9.0.13-jre11
# Set WORKDIR to root
WORKDIR .
# Copy target jar file into the image
COPY ./target/*.jar ./app.jar