package org.siu.myboot.server.service.impl;

import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.server.entity.po.User;
import org.siu.myboot.server.entity.qo.ChangePassword;
import org.siu.myboot.server.entity.qo.Login;
import org.siu.myboot.server.entity.qo.RegisteredUser;
import org.siu.myboot.server.service.GanxuAuthApiFeignService;

/**
 * 降级处理
 *
 * @Author Siu
 * @Date 2020/3/12 16:36
 * @Version 0.0.1
 */
public class GanxuAuthApiFeignServiceFallbackImpl implements GanxuAuthApiFeignService {
    @Override
    public Result<String> authorize(Login login) {
        return Result.error("降级处理 auth");
    }

    @Override
    public Result<Long> changePassword(ChangePassword changePassword) {
        return Result.error("降级处理 change pass");
    }

    @Override
    public Result<Boolean> signOut(String username) {
        return Result.error("降级处理 sign out");
    }

    @Override
    public Result<User> register(RegisteredUser params) {
        return Result.error("降级处理 register");
    }
}
