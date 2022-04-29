FROM maven:alpine as build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
RUN mvn clean install -DskipTests

FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8083
COPY --from=build /usr/app/target/transaction-0.0.1-SNAPSHOT.jar  /app/runner.jar
ENTRYPOINT java -jar /app/runner.jar