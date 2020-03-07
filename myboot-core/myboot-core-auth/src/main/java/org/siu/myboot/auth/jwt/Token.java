package org.siu.myboot.auth.jwt;

import io.jsonwebtoken.*;
import lombok.Getter;
import org.siu.myboot.auth.entity.AuthUser;
import org.siu.myboot.core.constant.Constant;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.Assert;
//import org.siu.myboot.core.exception.AuthenticateFail;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @Author Siu
 * @Date 2020/3/5 21:12
 * @Version 0.0.1
 */
@Getter
public class Token {
    /**
     * 是否合法
     */
    private boolean authorized;

    /**
     * token 字符串
     */
    private String token;

    /**
     * 解析后的token
     */
    private Jws<Claims> claimsJws;

    /**
     * 权限标识
     */
    Authentication authenticationToken;


    public Token(String token) {
        this.token = token;
    }

    /**
     * 解析
     *
     * @param key
     */
    public void parser(Key key) {
        this.claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(this.token);
        this.authorized = true;
    }


    /**
     * 获取token中的权限标识
     *
     * @return
     */
    public Authentication authentication() {
        if (this.authenticationToken == null) {
            Assert.notNull(this.claimsJws, "必须先解析token");
            Claims claims = this.claimsJws.getBody();
            this.authenticationToken = getAuthentication(claims, token);
        }
        return this.authenticationToken;
    }

    /**
     * 获取token中的权限标识
     *
     * @param claims
     * @param token
     * @return
     */
    private Authentication getAuthentication(Claims claims, String token) {
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(Constant.Auth.AUTHORITIES_KEY).toString().split(Constant.Auth.AUTHORITIES_SPLIT))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        AuthUser principal = new AuthUser(claims.getSubject(), "", authorities, Long.parseLong(claims.get(Constant.Auth.VERSION_KEY).toString()));

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }
}
