# Get tomcat image with java 10
FROM tomcat:9.0.13-jre10
# Default Environmental Variables
ENV profile development
# Set WORKDIR to root
WORKDIR .
# Copy target jar file into the image
COPY ./target/*.jar ./*.jar
# Run jar and set active spring profile
CMD ["java", "-jar", "-Dspring.profiles.active=${profile}", "app.jar"]