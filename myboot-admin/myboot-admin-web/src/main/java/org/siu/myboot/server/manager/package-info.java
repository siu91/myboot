/**
 * Manager层
 *
 * 通用业务处理层，它有如下特征：
 * 1） 对第三方平台封装的层，预处理返回结果及转化异常信息；
 * 2） 对Service层通用能力的下沉，如缓存方案、中间件通用处理；
 * 3） 与DAO层交互，对多个DAO的组合复用。
 *
 * @Author Siu
 * @Date 2020/2/19 20:10
 * @Version 0.0.1
 */
package org.siu.myboot.server.manager;