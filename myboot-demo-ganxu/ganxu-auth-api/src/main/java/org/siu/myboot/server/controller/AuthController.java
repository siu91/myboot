package org.siu.myboot.server.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.auth.util.SecurityUtils;
import org.siu.myboot.core.entity.AuthUser;
import org.siu.myboot.auth.jwt.TokenProvider;
import org.siu.myboot.core.config.web.limiting.Limiting;
import org.siu.myboot.core.constant.Constant;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.exception.AuthenticateFail;
import org.siu.myboot.core.exception.UserRegisterException;
import org.siu.myboot.core.exception.WrongUsernameOrPasswordException;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.controller.fallback.AuthBlockAndFallbackHandler;
import org.siu.myboot.server.entity.po.User;
import org.siu.myboot.server.entity.qo.ChangePassword;
import org.siu.myboot.server.entity.qo.Login;
import org.siu.myboot.server.entity.qo.RegisteredUser;
import org.siu.myboot.server.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * 统一认证、授权
 *
 * @Author Siu
 * @Date 2020/3/4 16:23
 * @Version 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/v1/api")
public class AuthController {

    @Resource
    private TokenProvider tokenProvider;

    @Resource
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Resource
    private UserService userService;


    /**
     * 认证、授权
     *
     * nacos 中熔断降级配置
     *
     *  [timeWindow]秒内发生[count]次异常，触发熔断降级，grade:0-RT，1-异常比例，2-异常次数
     *
     * [
     *   {
     *     "resource": "/v1/api/auth",
     *     "count": 20.0,
     *     "grade": 0,
     *     "passCount": 0,
     *     "timeWindow": 10
     *   },
     *   {
     *     "resource": "AuthControllerSentinelResource",
     *     "count": 1,
     *     "grade": 2,
     *     "passCount": 0,
     *     "timeWindow": 10
     *   }
     * ]
     *
     * nacos 中限流配置
     *
     * [
     *     {
     *         "resource": "AuthControllerSentinelResource",
     *         "limitApp": "default",
     *         "grade": 1,
     *         "count": 5,
     *         "strategy": 0,
     *         "controlBehavior": 0,
     *         "clusterMode": false
     *     }
     * ]
     *
     * @param login
     * @return
     * @throws WrongUsernameOrPasswordException
     */
    //@Limiting(limit = 1)
    @PostMapping("/auth")
    @SentinelResource(value = "AuthControllerSentinelResource", blockHandler = "blockHandler", blockHandlerClass = AuthBlockAndFallbackHandler.class,
            fallback = "fallbackHandler", fallbackClass = AuthBlockAndFallbackHandler.class)
    public Result<String> authorize(@Validated @RequestBody Login login) throws WrongUsernameOrPasswordException {
        /*if (System.currentTimeMillis() % 2 == 0) {
            //模拟随机异常
            int i = 1 / 0;
        }*/
        // 认证，通过并返回权限
        Authentication authentication = authentication(login.getUsername(), login.getPassword());

        // 颁发token
        boolean rememberMe = (login.getRememberMe() == null) ? false : login.getRememberMe();
        String jwt = tokenProvider.buildJWT(authentication, rememberMe);
        log.info("认证通过，给用户[{}],颁发token[{}]", login.getUsername(), jwt);

        return Result.ok(Constant.Auth.TOKEN_PREFIX + jwt);

    }

    /**
     * 修改密码
     *
     * @param changePassword
     * @return
     * @throws WrongUsernameOrPasswordException
     */
    @Limiting(limit = 1)
    @PostMapping("/password")
    @PreAuthorize("@pms.hasPermit('USER')")
    public Result<Long> changePassword(@Validated @RequestBody ChangePassword changePassword) throws WrongUsernameOrPasswordException, AuthenticateFail {
        // 0、验证当前用户与修改密码用户匹配
        Optional<AuthUser> currentUser = SecurityUtils.getCurrentUser();
        if (!currentUser.isPresent()) {
            throw new AuthenticateFail("当前用户不存在");
        } else {
            if (currentUser.get().getUsername().equals(changePassword.getUsername())) {
                // 1、先认证原密码
                Authentication authentication = authentication(changePassword.getUsername(), changePassword.getPassword());
                // 2、修改密码
                long result = userService.changePassword(changePassword, currentUser.get().getVersion());
                return Result.ok(result);

            } else {
                throw new AuthenticateFail("当前用户不匹配");
            }
        }


    }


    /**
     * 注销
     *
     * @param username
     * @return
     * @throws AuthenticateFail
     */
    @Limiting(limit = 1)
    @GetMapping("/signout/{username}")
    @PreAuthorize("@pms.hasPermit('USER')")
    public Result<Boolean> signOut(@PathVariable String username) throws AuthenticateFail {
        // 0、验证当前用户与修改密码用户匹配
        Optional<AuthUser> currentUser = SecurityUtils.getCurrentUser();
        if (!currentUser.isPresent()) {
            throw new AuthenticateFail("当前用户不存在");
        } else {
            if (currentUser.get().getUsername().equals(username)) {
                boolean signOut = userService.signOut(username);
                return Result.ok(signOut);
            } else {
                throw new AuthenticateFail("当前用户不匹配[" + username + "]");
            }
        }


    }


    /**
     * 用户注册/添加用户
     *
     * @param params
     * @return
     */
    @Limiting(limit = 1)
    @PostMapping("/register")
    @ApiOperation(value = "User:REGISTER")
    public Result<User> register(@RequestBody @Validated(Valid.CREATE.class) RegisteredUser params) throws UserRegisterException {
        User data = userService.register(
                new User().setPassword(params.getPassword()).setUserName(params.getUserName()).setPhone(params.getPhone()),
                params.getUserType());
        return Result.ok(data);
    }


    /* *//**
     * 认证、授权异常处理
     *
     * @param msg
     * @throws AuthenticateFail
     *//*
    @RequestMapping(value = "/auth/error", method = {RequestMethod.POST, RequestMethod.GET})
    public void error(String msg) throws AuthenticateFail {
        log.warn("AuthenticateFail:{}", msg);
        throw new AuthenticateFail(msg);

    }*/

    /**
     * 验证登录
     *
     * @param username 用户名/手机等ID
     * @param password 密码
     * @return
     */
    private Authentication authentication(String username, String password) throws WrongUsernameOrPasswordException {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication;
        try {
            // 通过 login.getUsername() 查找用户 org.siu.myboot.server.service.impl.UserDetailsServiceImpl.loadUserByUsername(login.getUsername())
            // 返回 org.springframework.security.core.userdetails.UserDetails
            // UserDetails中包含数据库中的密码和权限标识，密码与login.getPassword()比较，密码错误会抛错 WrongUsernameOrPasswordException
            authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            throw new WrongUsernameOrPasswordException(username);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }


}
