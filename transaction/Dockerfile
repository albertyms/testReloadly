FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8083
ADD target/transaction-0.0.1-SNAPSHOT.jar transaction-0.0.1.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/transaction-0.0.1.jar"]