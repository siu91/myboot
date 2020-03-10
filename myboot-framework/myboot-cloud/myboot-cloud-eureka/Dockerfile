FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER Siu

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /myboot-cloud-eureka

WORKDIR /myboot-cloud-eureka

EXPOSE 8000 8000
EXPOSE 8001 8001
EXPOSE 8002 8002

ADD ./target/myboot-cloud-eureka-0.0.1.jar ./

ADD ./bin/startup.sh ./

#CMD sh ./startup.sh
#CMD echo "127.0.0.1 eureka-replica0,eureka-replica1,eureka-replica2">> /etc/hosts;sleep 10;java -Djava.security.egd=file:/dev/./urandom -jar myboot-cloud-eureka-0.0.1.jar --spring.profiles.active=replica0;sleep 10;java -Djava.security.egd=file:/dev/./urandom -jar myboot-cloud-eureka-0.0.1.jar --spring.profiles.active=replica1;sleep 10;java -Djava.security.egd=file:/dev/./urandom -jar myboot-cloud-eureka-0.0.1.jar --spring.profiles.active=replica2
