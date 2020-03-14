







## Common property configuration



| name                              | description                                                  | option                                                       |
| --------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| MODE                              | cluster/standalone                                           | cluster/standalone default **cluster**                       |
| NACOS_SERVERS                     | nacos cluster address                                        | eg. ip1:port1 ip2:port2 ip3:port3                            |
| PREFER_HOST_MODE                  | Whether hostname are supported                               | hostname/ip default **ip**                                   |
| NACOS_SERVER_PORT                 | nacos server port                                            | default **8848**                                             |
| NACOS_SERVER_IP                   | custom nacos server ip when network was mutil-network        |                                                              |
| SPRING_DATASOURCE_PLATFORM        | standalone support mysql                                     | mysql / empty default empty                                  |
| MYSQL_SERVICE_HOST                | mysql host                                                   |                                                              |
| MYSQL_SERVICE_PORT                | mysql database port                                          | default : **3306**                                           |
| MYSQL_SERVICE_DB_NAME             | mysql database name                                          |                                                              |
| MYSQL_SERVICE_USER                | username of database                                         |                                                              |
| MYSQL_SERVICE_PASSWORD            | password of database                                         |                                                              |
| ~~MYSQL_MASTER_SERVICE_HOST~~     | The **latest** version of the image removes this attribute, using MYSQL_SERVICE_HOST |                                                              |
| ~~MYSQL_MASTER_SERVICE_PORT~~     | The **latest** version of the image removes this attribute, using MYSQL_SERVICE_PORT | default : **3306**                                           |
| ~~MYSQL_MASTER_SERVICE_DB_NAME~~  | The **latest** version of the image removes this attribute, using MYSQL_SERVICE_DB_NAME |                                                              |
| ~~MYSQL_MASTER_SERVICE_USER~~     | The **latest** version of the image removes this attribute, using MYSQL_SERVICE_USER |                                                              |
| ~~MYSQL_MASTER_SERVICE_PASSWORD~~ | The **latest** version of the image removes this attribute, using MYSQL_SERVICE_PASSWORD |                                                              |
| ~~MYSQL_SLAVE_SERVICE_HOST~~      | The **latest** version of the image removes this attribute   |                                                              |
| ~~MYSQL_SLAVE_SERVICE_PORT~~      | The **latest** version of the image removes this attribute   | default :3306                                                |
| MYSQL_DATABASE_NUM                | It indicates the number of database                          | default :**1**                                               |
| JVM_XMS                           | -Xms                                                         | default :2g                                                  |
| JVM_XMX                           | -Xmx                                                         | default :2g                                                  |
| JVM_XMN                           | -Xmn                                                         | default :1g                                                  |
| JVM_MS                            | -XX:MetaspaceSize                                            | default :128m                                                |
| JVM_MMS                           | -XX:MaxMetaspaceSize                                         | default :320m                                                |
| NACOS_DEBUG                       | enable remote debug                                          | y/n default :n                                               |
| TOMCAT_ACCESSLOG_ENABLED          | server.tomcat.accesslog.enabled                              | default :false                                               |
| NACOS_AUTH_SYSTEM_TYPE            | The auth system to use, currently only 'nacos' is supported  | default :nacos                                               |
| NACOS_AUTH_ENABLE                 | If turn on auth system                                       | default :false                                               |
| NACOS_AUTH_TOKEN_EXPIRE_SECONDS   | The token expiration in seconds                              | default :18000                                               |
| NACOS_AUTH_TOKEN                  | The default token                                            | default :SecretKey012345678901234567890123456789012345678901234567890123456789 |
| NACOS_AUTH_CACHE_ENABLE           | Turn on/off caching of auth information. By turning on this switch, the update of auth information would have a 15 seconds delay. |                                                              |