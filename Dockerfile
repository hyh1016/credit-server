FROM openjdk:21-jdk

ENV JAR_NAME=credit-server.jar
ENV JAR_PATH=build/libs/$JAR_NAME
ENV STAGE=container

COPY $JAR_PATH .

CMD java -jar $JAR_NAME --spring.profiles.active=$STAGE
