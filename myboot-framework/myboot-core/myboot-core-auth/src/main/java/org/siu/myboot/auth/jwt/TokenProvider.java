package org.siu.myboot.auth.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.AuthUser;
import org.siu.myboot.core.constant.Constant;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
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
     * 签名key
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
    public String buildJWT(Authentication authentication, boolean rememberMe) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(Constant.Auth.AUTHORITIES_SPLIT));

        // 过期时间处理
        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInSecondsForRememberMe * 1000);
        } else {
            validity = new Date(now + this.tokenValidityInSeconds * 1000);
        }

        long version = -1;
        // 获取用户的版本信息
        if (authentication.getPrincipal() instanceof AuthUser) {
            version = ((AuthUser) authentication.getPrincipal()).getVersion();
        }

        // 构建token信息
        return buildJWT(authentication.getName(), authorities, validity, version);
    }


    /**
     * @param subject
     * @param authorities
     * @param validity
     * @return
     */
    public String buildJWT(String subject, String authorities, Date validity, long version) {
        // 构建token信息
        return Jwts.builder()
                // 该JWT的签发者
                .setIssuer("myboot TokenProvider")
                // 该JWT所面向的用户:放入用户信息（用户名）
                .setSubject(subject)
                // 接收该JWT的一方
                .setAudience("ganxu")
                // 放入权限信息
                .claim(Constant.Auth.AUTHORITIES_KEY, authorities)
                // 用户信息版本
                .claim(Constant.Auth.VERSION_KEY, version)
                // 签名
                .signWith(key, SignatureAlgorithm.HS512)
                // 过期时间
                .setExpiration(validity)
                // 生效开始时间
                .setNotBefore(new Date())
                // 在什么时候签发的
                .setIssuedAt(new Date())
                .compact();
    }


    /**
     * 给快过期的token续期
     *
     * @param token 原token
     * @return
     */
    public String refresh(Token token) {
        // 默认策略：续期为原有效时间的十分之一
        if (token.isAuthorized()) {
            Claims claims = token.getClaimsJws().getBody();
            // 如果快失效了半小时
            if ((claims.getExpiration().getTime() - System.currentTimeMillis()) < Constant.Auth.REFRESH_TOKEN_TIME_THRESHOLD_MS) {
                if (claims.getExpiration() != null && claims.getNotBefore() != null) {
                    long renewTime = claims.getExpiration().getTime() +
                            Math.max(Constant.Auth.REFRESH_TOKEN_RENEW_TIME_MS, ((claims.getExpiration().getTime() - claims.getIssuedAt().getTime()) / 10));
                    Date validity = new Date(renewTime);
                    log.info("用户[{}]的token快失效了-原失效时间[{}],自动续期到[{}]", token.getClaimsJws().getBody().getSubject(), claims.getExpiration(), validity);
                    return buildJWT(claims.getSubject(), claims.get(Constant.Auth.AUTHORITIES_KEY).toString(), validity, Long.parseLong(claims.get(Constant.Auth.VERSION_KEY).toString()));
                }
            }
        }

        return "";
    }


    /**
     * 校验token
     *
     * @param authToken
     * @return
     */
    public Token validate(String authToken) {
        Token token = new Token(authToken);
        try {
            token.parser(key);
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
        return token;
    }

}
