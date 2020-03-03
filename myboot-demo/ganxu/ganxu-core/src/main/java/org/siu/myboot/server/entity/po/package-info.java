/**
 * 约定为PO层
 *
 * 1、PO：persistent object 持久对象,也叫 DO（Data Object）
 * 2、对应数据库中的entity，可以简单认为一个PO对应数据库中的一条记录。
 * 3、PO中不应该包含任何对数据库的操作。
 *
 *
 *  * QUERY/QO ：数据查询对象 (Data Query Object)，由于查询对象有 QueryDSL 自动生成，故和 PO/DO放在一起
 *  *
 *  * 各层接收上层的查询请求。注：超过 2 个参数的查询封装，禁止使用 Map 类来传输。
 *
 * @Author Siu
 * @Date 2020/2/19 8:55
 * @Version 0.0.1
 */
package org.siu.myboot.server.entity.po;