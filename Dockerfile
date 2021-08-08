FROM openjdk:8-jre-alpine
MAINTAINER Elvert Mora

RUN mkdir /code
COPY target/*.jar /code

EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "exec java -jar -Duser.timezone=$TIMEZONE -Dnetworkaddress.cache.ttl=60 -Dnetworkaddress.cache.negative.ttl=30 /code/*.jar" ]
