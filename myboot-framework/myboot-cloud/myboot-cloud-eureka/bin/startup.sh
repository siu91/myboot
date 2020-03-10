#!/bin/bash
#
# @CreationTime
#   2020/3/10 下午18:26:43
# @ModificationDate
#   2020/3/10 下午18:26:43
# @Function
#  在Docker中启动多个引用实例
# @Usage
#
#
# @author Siu

# 设置eureka主机名
echo "127.0.0.1 eureka-replica0,eureka-replica1,eureka-replica2">> /etc/hosts
# 启动服务
nohup java -Djava.security.egd=file:/dev/./urandom -jar myboot-cloud-eureka-0.0.1.jar --spring.profiles.active=replica0 >/dev/null 2>&1 &
nohup java -Djava.security.egd=file:/dev/./urandom -jar myboot-cloud-eureka-0.0.1.jar --spring.profiles.active=replica1 >/dev/null 2>&1 &
nohup java -Djava.security.egd=file:/dev/./urandom -jar myboot-cloud-eureka-0.0.1.jar --spring.profiles.active=replica2 >/dev/null 2>&1 &

sleep 10000000