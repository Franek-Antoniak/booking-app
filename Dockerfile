FROM maven:3.8.7-amazoncorretto-19 AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME

COPY pom.xml $APP_HOME/
COPY cinema-model/pom.xml $APP_HOME/cinema-model/
COPY cinema-web/pom.xml $APP_HOME/cinema-web/

COPY . $APP_HOME/

RUN mvn clean package

FROM openjdk:19-alpine
ENV ARTIFACT_NAME=cinema-web-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME

EXPOSE 8080

COPY --from=TEMP_BUILD_IMAGE $APP_HOME/cinema-web/target/$ARTIFACT_NAME .

ENTRYPOINT exec java -jar $ARTIFACT_NAME