FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/DoctorGetAllService-0.0.1-SNAPSHOT.jar /app/myapp.jar

EXPOSE 8081

CMD ["java", "-jar", "myapp.jar"]