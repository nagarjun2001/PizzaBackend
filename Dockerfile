FROM openjdk:18
WORKDIR /app
COPY ./target/Pizza-0.0.1-SNAPSHOT.jar /app
EXPOSE 1234
CMD ["java", "-jar", "Pizza-0.0.1-SNAPSHOT.jar"]