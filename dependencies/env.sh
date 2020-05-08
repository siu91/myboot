#!/bin/bash
#
# @CreationTime
#   2020/3/13 下午18:16:25
# @ModificationDate
#   2020/3/13 下午18:16:25
# @Function
#  本项目中用到的环境
# @Usage
#
#
# @author Siu

# zipkin
docker pull openzipkin/zipkin
docker run -d -p 9411:9411 openzipkin/zipkin

# hystrix-dashboard
#docker pull kennedyoliveira/hystrix-dashboard
#docker run --rm -ti -p 7979:7979 kennedyoliveira/hystrix-dashboard
#或指定参数 docker run --rm -ti -p 7979:7979 -e JVM_ARGS='-Xmx2048m' kennedyoliveira/hystrix-dashboard

# 阿里sentinel-dashboard（流量卫兵） 账号/密码 sentinel/sentinel
# 相等于hystrix-dashboard
docker pull bladex/sentinel-dashboard
docker run --name sentinel  -d -p 8858:8858 -d  bladex/sentinel-dashboard:latest

# OSS minio
docker pull minio/minio
docker run -p 9000:9000 minio/minio server /data

# redis https://hub.docker.com/_/redis
docker pull redis:latest
docker run -itd --name redis-test -p 6379:6379 redis

# ali nacos
docker pull nacos/nacos-server:latest
docker run -d --name nacos -e PREFER_HOST_NAME=hostname -e MODE=standalone -e SPRING_DATASOURCE_PLATFORM=empty -p 8848:8848 nacos/nacos-server

# 携程apollo https://github.com/ctripcorp/apollo/wiki/Apollo-Quick-Start-Docker%E9%83%A8%E7%BD%B2
# docker-quick-start目录下执行docker-compose up