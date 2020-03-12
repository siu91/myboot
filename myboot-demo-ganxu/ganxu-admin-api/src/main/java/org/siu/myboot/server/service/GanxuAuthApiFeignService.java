package org.siu.myboot.server.service;

import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.server.entity.po.User;
import org.siu.myboot.server.entity.qo.ChangePassword;
import org.siu.myboot.server.entity.qo.Login;
import org.siu.myboot.server.entity.qo.RegisteredUser;
import org.siu.myboot.server.service.impl.GanxuAuthApiFeignServiceFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * GanxuAuthApiFeignService
 * <p>
 * 绑定 ganxu-auth-api 服务，GanxuAuthApiFeignServiceFallbackImpl 为降级处理
 *
 * @Author Siu
 * @Date 2020/3/12 16:32
 * @Version 0.0.1
 */
@FeignClient(value = "ganxu-auth-api", fallback = GanxuAuthApiFeignServiceFallbackImpl.class)
public interface GanxuAuthApiFeignService {

    /**
     * 认证、授权
     *
     * @param login
     * @return
     */
    @PostMapping("/v1/api/auth")
    Result<String> authorize(@RequestBody Login login);

    /**
     * 修改密码
     *
     * @param changePassword
     * @return
     */
    @PostMapping("/v1/api/password")
    Result<Long> changePassword(@RequestBody ChangePassword changePassword);


    /**
     * 注销
     *
     * @param username
     * @return
     */
    @GetMapping("/v1/api/signout/{username}")
    Result<Boolean> signOut(@PathVariable String username);


    /**
     * 用户注册/添加用户
     *
     * @param params
     * @return
     */
    @PostMapping("/v1/api/register")
    Result<User> register(@RequestBody RegisteredUser params);


}
