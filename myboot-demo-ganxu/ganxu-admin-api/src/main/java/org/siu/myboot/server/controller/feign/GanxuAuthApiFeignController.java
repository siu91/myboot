package org.siu.myboot.server.controller.feign;

import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.server.entity.po.User;
import org.siu.myboot.server.entity.qo.ChangePassword;
import org.siu.myboot.server.entity.qo.Login;
import org.siu.myboot.server.entity.qo.RegisteredUser;
import org.siu.myboot.server.service.GanxuAuthApiFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 统一认证、授权（调用ganxu-auth-api服务）
 *
 * @Author Siu
 * @Date 2020/3/4 16:23
 * @Version 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class GanxuAuthApiFeignController {


    private final GanxuAuthApiFeignService ganxuAuthApiFeignService;

    @Autowired
    public GanxuAuthApiFeignController(GanxuAuthApiFeignService ganxuAuthApiFeignService) {
        this.ganxuAuthApiFeignService = ganxuAuthApiFeignService;
    }

    /**
     * 认证、授权
     *
     * @param login
     * @return
     */
    @PostMapping("/auth")
    public Result<String> authorize(@RequestBody Login login) {
        return ganxuAuthApiFeignService.authorize(login);
    }

    /**
     * 修改密码
     *
     * @param changePassword
     * @return
     */
    @PostMapping("/password")
    public Result<Long> changePassword(@RequestBody ChangePassword changePassword) {
        return ganxuAuthApiFeignService.changePassword(changePassword);
    }


    /**
     * 注销
     *
     * @param username
     * @return
     */
    @GetMapping("/signout/{username}")
    public Result<Boolean> signOut(@PathVariable String username) {
        return ganxuAuthApiFeignService.signOut(username);

    }


    /**
     * 用户注册/添加用户
     *
     * @param params
     * @return
     */
    @PostMapping("/register")
    public Result<User> register(@RequestBody RegisteredUser params) {
        return ganxuAuthApiFeignService.register(params);

    }


}
