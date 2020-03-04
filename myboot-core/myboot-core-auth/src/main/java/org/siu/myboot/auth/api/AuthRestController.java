package org.siu.myboot.auth.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.siu.myboot.auth.api.dto.LoginDTO;
import org.siu.myboot.auth.jwt.JWTFilter;
import org.siu.myboot.auth.jwt.TokenProvider;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.exception.WrongUsernameOrPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 统一认证接口
 *
 * @Author Siu
 * @Date 2020/3/4 16:23
 * @Version 0.0.1
 */
@RestController
@RequestMapping("/v1/api")
public class AuthRestController {

    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;


    @PostMapping("/auth")
    public Result authorize(@Valid @RequestBody LoginDTO login) throws WrongUsernameOrPasswordException {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
        Authentication authentication = null;
        try {
            // 通过 login.getUsername() 查找用户 org.siu.myboot.server.service.impl.UserDetailsServiceImpl.loadUserByUsername(login.getUsername())
            // 返回 org.springframework.security.core.userdetails.UserDetails
            // UserDetails中包含数据库中的密码和权限标识，密码与login.getPassword()比较，密码错误会抛错 WrongUsernameOrPasswordException
            authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            throw new WrongUsernameOrPasswordException(login.getUsername());
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

        boolean rememberMe = (login.getRememberMe() == null) ? false : login.getRememberMe();
        String jwt = tokenProvider.createToken(authentication, rememberMe);

        //HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new Result("Bearer " + jwt);

    }

}
