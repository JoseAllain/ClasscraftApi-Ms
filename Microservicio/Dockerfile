FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/micro.jar micro.jar
EXPOSE 8080
CMD ["java","-jar","micro.jar"]