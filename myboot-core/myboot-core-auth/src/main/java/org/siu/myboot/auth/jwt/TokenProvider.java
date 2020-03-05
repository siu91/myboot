package org.siu.myboot.auth.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.constant.Constant;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * token 生成
 *
 * @Author Siu
 * @Date 2020/3/4 15:01
 * @Version 0.0.1
 */
@Slf4j
@Component
public class TokenProvider implements InitializingBean {


    /**
     * base64-secret
     * 设置默认值，也可以从配置文件中配置
     */
    @Value("${jwt.base64-secret:ZmQ0ZGI5NjQ0MDQwY2I4MjMxY2Y3ZmI3MjdhN2ZmMjNhODViOTg1ZGE0NTBjMGM4NDA5NzYxMjdjOWMwYWRmZTBlZjlhNGY3ZTg4Y2U3YTE1ODVkZDU5Y2Y3OGYwZWE1NzUzNWQ2YjFjZDc0NGMxZWU2MmQ3MjY1NzJmNTE0MzI=}")
    private String base64Secret;

    /**
     * 默认token失效时间
     * 设置默认值，也可以从配置文件中配置
     */
    @Value("${jwt.token-validity-in-seconds:86400}")
    private long tokenValidityInSeconds;

    /**
     * 记住密码时token失效时间
     * 设置默认值，也可以从配置文件中配置
     */
    @Value("${jwt.token-validity-in-seconds-for-remember-me:108000}")
    private long tokenValidityInSecondsForRememberMe;

    /**
     *  签名key
     */
    private Key key;


    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * @param authentication
     * @param rememberMe
     * @return
     */
    public String createToken(Authentication authentication, boolean rememberMe) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // 过期时间处理
        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInSecondsForRememberMe * 1000);
        } else {
            validity = new Date(now + this.tokenValidityInSeconds * 1000);
        }

        // 构建token信息
        return Jwts.builder()
                // 放入用户信息（用户名）
                .setSubject(authentication.getName())
                // 放入权限信息
                .claim(Constant.Auth.AUTHORITIES_KEY, authorities)
                // 签名
                .signWith(key, SignatureAlgorithm.HS512)
                // 过期时间
                .setExpiration(validity)
                .compact();
    }

    /**
     * 从token 中获取用户的权限
     *
     * @param token
     * @return
     */
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(Constant.Auth.AUTHORITIES_KEY).toString().split(Constant.Auth.AUTHORITIES_SPLIT))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    /**
     * @param authToken
     * @return
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: ", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            log.trace("Expired JWT token trace: ", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: ", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: ", e);
        }
        return false;
    }

}
