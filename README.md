[![License](https://img.shields.io/github/license/apache/incubator-streampipes.svg)](http://www.apache.org/licenses/LICENSE-2.0)


## 简介
myboot是一个基于springboot的项目脚手架。
<img src="./LOGO.png" alt="myboot" style="zoom:75%;" />



## 技术栈



| 技术                 | 说明                | 官网                                              |
| -------------------- | ------------------- | ------------------------------------------------- |
| SpringBoot           | 容器+MVC框架        | https://spring.io/projects/spring-boot            |
| Spring Data JPA      | ORM框架             | https://spring.io/projects/spring-data-jpa        |
| MyBatis              | ORM框架（考虑移掉） | http://www.mybatis.org/mybatis-3/zh/index.html    |
| Hibernator-Validator | 验证框架            | http://hibernate.org/validator                    |
| Querydsl             | 结构化查询工具      | http://www.querydsl.com/                          |
| HikariCP             | 号称最快的连接池    | https://github.com/brettwooldridge/HikariCP       |
| p6spy                | sql监控             | https://github.com/p6spy/p6spy                    |
| Lombok               | 简化对象封装工具    | https://github.com/rzwitserloot/lombok            |
| Swagger-UI           | API文档工具         | https://github.com/swagger-api/swagger-ui         |
| RabbitMq/Kafka       | 消息队列（待选型）  | https://www.rabbitmq.com/                         |
| Redis                | 分布式缓存          | https://redis.io/                                 |
| MinIO/FastDFS        | 对象存储（待选型）  | https://github.com/minio/minio                    |
| Docker               | 应用容器引擎        | [https://www.docker.com](https://www.docker.com/) |
| Jenkins              | 自动化部署工具      | https://github.com/jenkinsci/jenkins              |

***

   ###  项目结构

  ~~~
   .
├── LICENSE					license 文件
├── README.md					README
├── myboot-admin				后台系统demo
├── myboot-combusi				通用业务模块
├── myboot-component			        组件模块
└── myboot-core					核心模块
  ~~~

## 使用

* git clone 
* mvn compile


## 计划

TODO:
1. 消息队列选型
2. OSS选型
3. 引入 p6spy、Querydsl
4. 分库分表选型


## Feedback

 [gshiwen@gmail.com](mailto:gshiwen@gmail.com)

## License

[Apache License 2.0](LICENSE)




