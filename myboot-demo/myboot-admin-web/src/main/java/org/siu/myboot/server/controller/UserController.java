/*
package org.siu.myboot.server.controller;

import org.siu.myboot.server.entity.po.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

*/
/**
 * RESTful API Demo
 *
 * @Author Siu
 * @Date 2020/2/16 19:11
 * @Version 0.0.1
 *//*

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping(path = "/{userId}")
    public User get(@PathVariable String userId) {
        User user = new User();
        user.setId(0L);
        user.setName("小明");
        user.setAge(15);
        return user;

    }

    @PostMapping
    public User create(@Valid User user) {
        System.out.println("创建一个用户：" + user.getName());
        return user;
    }

    @DeleteMapping(path = "/{userId}")
    public User delete(@PathVariable String userId) {
        User user = new User();
        user.setId(0L);
        user.setName("小明");
        user.setAge(12);
        return user;

    }


}
*/
