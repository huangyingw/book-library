FROM openjdk:8-jre-alpine

WORKDIR /usr/src/app
COPY /build/libs/BookLibrary-0.0.1-SNAPSHOT.jar /usr/src/app

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "BookLibrary-0.0.1-SNAPSHOT.jar"]