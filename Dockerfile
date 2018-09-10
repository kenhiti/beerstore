FROM openjdk:8u171-jdk-alpine3.8
LABEL maintainer="kenhitifujimoto@hotmail.com"

ENV LANG C.UTF-8

RUN apk add --update bash

COPY docker-entrypoint.sh /usr/local/bin
ENTRYPOINT ["docker-entrypoint.sh"]

ADD build/libs/*.jar /app/app.jar

EXPOSE 8080

#HEALTHCHECK --timeout=3s CMD wget -q -0 /dev/null http://localhost:8080/actuator/health || exit 1

CMD ["/app/app.jar"]