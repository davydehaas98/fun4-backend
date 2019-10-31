# FUN4 backend

This project is meant to serve as the backend for a FUN4-project. It's writting in Java using the Spring framework.

## Deployment
Deployment is done using Docker. To build the container, execute the following command:

    docker build -t fun4-backend .

This command will build an image for you to run. To run this image, execute the following command:

    docker run -d fun4-backend
