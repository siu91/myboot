/*
package org.siu.myboot.server.dao;

import org.siu.myboot.server.model.po.User;
import org.siu.myboot.server.model.qo.UserParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

*/
/**
 * @Author Siu
 * @Date 2020/2/17 22:09
 * @Version 0.0.1
 *//*

public interface UserMapper {

    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "age", column = "age"),
            @Result(property = "name", column = "name")
    })
    List<User> getAll();


    @Select("SELECT * FROM users WHERE name=#{name} AND age = #{age}")
    List<User> getListByNameAndAge(Map<String, Object> map);

    @SelectProvider(type = UserSql.class, method = "getCount")
    int getCount(UserParam userParam);

    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "age", column = "age", javaType = Integer.class),
            @Result(property = "name", column = "age")
    })
    User getOne(Long id);

    @Insert("INSERT INTO users(name,pass,age) VALUES(#{name}, #{pass}, #{age})")
    void insert(User user);

    @Update("UPDATE users SET name=#{name},age=#{age} WHERE id =#{id}")
    void update(User user);

    @Update({"<script> ",
            "update users ",
            "<set>",
            " <if test='name != null'>name=#{name}</if>",
            " </set> ",
            "where id=#{id} ",
            "</script>"})
    void updateUser(User user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    void delete(Long id);

}*/
