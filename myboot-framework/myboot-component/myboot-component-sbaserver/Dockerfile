FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER Siu

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /myboot-component-sbaserver

WORKDIR /myboot-component-sbaserver

ENV PORT_TO_EXPOSE=8201

EXPOSE $PORT_TO_EXPOSE 9000

ADD ./target/myboot-component-sbaserver-0.0.1.jar ./

CMD sleep 60;java -Djava.security.egd=file:/dev/./urandom -jar myboot-component-sbaserver-0.0.1.jar
