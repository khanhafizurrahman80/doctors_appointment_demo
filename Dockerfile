FROM openjdk:8-jdk-alpine
MAINTAINER khan hafizur rahman <khr.1380@gmail.com>
VOLUME /tmp
EXPOSE 8080
COPY doctorsappointment-web/target/docker-doctor-appointment.jar docker-doctor-appointment.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/docker-doctor-appointment.jar"]