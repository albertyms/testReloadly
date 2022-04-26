FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8082
ADD target/notification-0.0.1-SNAPSHOT.jar notification-0.0.1.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/notification-0.0.1.jar"]