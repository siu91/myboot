[![License](https://img.shields.io/github/license/apache/incubator-streampipes.svg)](http://www.apache.org/licenses/LICENSE-2.0)


## 简介
myboot是一个基于Spring Boot/Spring Cloud的项目脚手架/Demo。
<img src="./LOGO.png" alt="myboot" style="zoom:75%;" />



## 技术栈

- Spring Boot 相关基础：

| 技术                 | 说明                | 官网                                                         |
| -------------------- | ------------------- | ------------------------------------------------------------ |
| SpringBoot           | 容器+MVC框架        | https://spring.io/projects/spring-boot                       |
| Spring Data JPA      | ORM框架             | https://spring.io/projects/spring-data-jpa                   |
| Hibernator-Validator | 验证框架            | http://hibernate.org/validator                               |
| Spring Security      | 安全框架            | https://spring.io/projects/                                  |
| JWT                  | Tokens方案          | https://jwt.io/                                              |
| QueryDSL             | 结构化查询工具      | http://www.querydsl.com/                                     |
| HikariCP             | 号称最快的连接池    | https://github.com/brettwooldridge/HikariCP                  |
| p6spy                | sql监控             | https://github.com/p6spy/p6spy                               |
| Lombok               | 简化对象封装工具    | https://github.com/rzwitserloot/lombok                       |
| Swagger-UI           | API文档工具         | https://github.com/swagger-api/swagger-ui                    |
| RabbitMq/Kafka       | 消息队列（待选型）  | https://www.rabbitmq.com/                                    |
| Redis                | 分布式缓存          | https://redis.io/                                            |
| MinIO                | 对象存储            | https://github.com/minio/minio https://hub.docker.com/r/minio/minio/ |
| Spring Boot Admin    | springboot 服务监控 | https://spring.io/projects/                                  |
| flyway               | 数据库脚本管理      | https://flywaydb.org/                                        |
| apt-maven-plugin     | querydsl Maven插件  | https://github.com/querydsl/apt-maven-plugin                 |
| Docker               | 应用容器引擎        | [https://www.docker.com](https://www.docker.com/)            |
| Jenkins              | 自动化部署工具      | https://github.com/jenkinsci/jenkins                         |

***



- **Spring Cloud**

  ![spring-cloud](./assets/spring-cloud.png)

  - ~~Eureka 服务注册&发现框架~~ （使用Nacos代替）
  - Ribbon 进程内负载均衡器
  - Open Feign 服务调用映射
  - ~~Hystrix 服务降级熔断器~~（使用Sentinel代替）
  - Zuul 微服务网关
  - Config 微服务统一配置中心 （使用Nacos代替）
  - ~~Bus 消息总线~~
  - **集成Spring Cloud Alibaba 补充相关功能:**
    - 服务限流降级：使用 Sentinel 进行流量控制，熔断降级以及系统保护等多个维度保护服务稳定性（**完成**）
    - 服务注册与发现：使用 Nacos 适配 Spring Cloud 服务注册与发现标准，默认集成了 Ribbon 这个客户端负载均衡组件（**完成**）
      - 通过 Nacos Server 和 spring-cloud-starter-alibaba-nacos-discovery 实现服务的注册与发现
    - 分布式配置管理：以 Nacos 作为数据存储支持分布式系统中的外部化配置，配置更改时自动刷新（**完成**）
      - 通过 Nacos Server 和 spring-cloud-starter-alibaba-nacos-config 实现配置的动态变更。
    - 事件驱动：使用 Spring Cloud Stream RocketMQ Binder 来构建事件驱动的微服务实例（**进行中**）
    - 消息总线：使用 Spring Cloud Bus RocketMQ 连接分布式系统中的各个节点（**进行中**）
    - 分布式事务：使用 Seata 高效并且对业务零侵入地解决分布式事务问题（**进行中**）
    - Dubbo RPC：扩展 Spring Cloud 服务调用协议，可使用 Spring Cloud 客户端调用 Dubbo 服务（未计划集成）
    - 阿里云 OSS 集成: 使用阿里云 OSS 服务集成 Spring Resource 资源（未计划集成）

- 其它技术栈首选Spring 内成熟的技术

![spring-stack](./assets/spring-stack.png)



主要功能：

- 结构化查询：Spring JPA + QueryDSL ，提供基础单表查询，复杂查询DSL封装

- 多数据源自动配置

- p6spy 自动配置

- p6spy 监控sql，自定义日志

- [redis + aop 实现限流](./myboot-framework/myboot-core/myboot-core-config/src/main/java/org/siu/myboot/core/config/web/limiting/LimitingAspect.java)

- 全局的参数校验处理

- 全局异常处理

- 接口统一格式返回

- 分页封装

- minio 对象存储封装

- flyway 数据库脚本管理

- IDEA + groovy 自动代码生成

- Swagger 生成API 文档

- MDC 实现日志追踪 traceId （RPC、Http、异步线程**待处理**）

- Spring Security + JWT 实现认证和授权、单点登录

  

## 项目结构

~~~
 .
├── LICENSE					license 文件
├── README.md					README
├── myboot-demo	         			后台系统demo
├── myboot-commonbiz				通用业务模块
├── myboot-component			        组件模块
└── myboot-core					核心模块
~~~

## 使用

* git clone 
* mvn compile


## 计划

TODO:
1. 引入Spring Coud
2. 集成 Spring Coud Alibaba
3. 消息队列选型
4. 前端技术选型 Vue/React
5. 分库分表选型


## Feedback

 [gshiwen@gmail.com](mailto:gshiwen@gmail.com)

## License

[Apache License 2.0](LICENSE)




