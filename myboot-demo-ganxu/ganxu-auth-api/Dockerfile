FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER Siu

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /ganxu-auth-api

WORKDIR /ganxu-auth-api

ENV PORT_TO_EXPOSE=9501

EXPOSE $PORT_TO_EXPOSE 9501

ADD ./target/ganxu-auth-api-0.0.1.jar ./

CMD sleep 10;java -Djava.security.egd=file:/dev/./urandom -jar ganxu-auth-api-0.0.1.jar
