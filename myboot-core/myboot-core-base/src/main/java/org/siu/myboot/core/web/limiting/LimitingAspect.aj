package org.siu.myboot.core.web.limiting;


import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.siu.myboot.core.constant.Constants;
import org.siu.myboot.core.exception.OverLimitException;
import org.siu.myboot.core.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 限流切面
 *
 * @Author Siu
 * @Date 2020/3/3 12:10
 * @Version 0.0.1
 */
@Slf4j
@Configuration
@Aspect
public abstract aspect LimitingAspect {

    //@Autowired
    private RedisTemplate<String, Serializable> limitRedisTemplate;

    @Autowired
    public LimitingAspect(RedisTemplate<String, Serializable> limitRedisTemplate) {
        this.limitRedisTemplate = limitRedisTemplate;
    }


    /**
     * 定义切入点:匹配带Log注解的方法
     */
    @Pointcut("@annotation(org.siu.myboot.core.web.limiting.Limiting)")
    public void whenMethodsAnnotationLimiting() {
    }

    /**
     * 定义切入点:所有 controllers（必须命名为Controller）
     */
    /*@Pointcut("execution(* org.siu.myboot.server.controller.*Controller.*(..))")
    private void controllers() {
    }*/


    /**
     * 使用@Limiting注解控制切面
     *
     * @param pjp
     * @return
     */
    @Around("whenMethodsAnnotationLimiting()")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Limiting limitAnnotation = method.getAnnotation(Limiting.class);
        LimitingType limitType = limitAnnotation.type();
        String name = limitAnnotation.name();
        String key;
        // 限流周期
        int limitPeriod = limitAnnotation.period();
        // 限流周期内可请求次数
        int limitCount = limitAnnotation.limit();
        switch (limitType) {
            case IP:
                key = getIpAddress();
                break;
            case CUSTOM:
                key = limitAnnotation.key();
                break;
            default:
                key = method.getName().toUpperCase();
        }
        ImmutableList<String> keys = ImmutableList.of(Strings.join(limitAnnotation.prefix(), key));
        try {
            String luaScript = limitingLuaScript();
            RedisScript<Number> redisScript = new DefaultRedisScript<>(luaScript, Number.class);
            Number count = limitRedisTemplate.execute(redisScript, keys, limitCount, limitPeriod);
            log.info("Access try count is {} for name={} and key = {}", count, name, key);
            if (count != null && count.intValue() <= limitCount) {
                return pjp.proceed();
            } else {
                throw new OverLimitException("超出流量，当前流量:" + count + ",限流:" + limitCount);
            }
        } catch (Throwable e) {
            if (e instanceof RuntimeException) {
                throw new RuntimeException(e.getLocalizedMessage());
            }
            throw new RuntimeException("server exception");
        }
    }

    /**
     * 限流Lua脚本
     *
     * @return lua脚本
     */
    public String limitingLuaScript() {
        return "local c" +
                "\nc = redis.call('get',KEYS[1])" +
                // 调用不超过最大值，则直接返回
                "\nif c and tonumber(c) > tonumber(ARGV[1]) then" +
                "\n" +
                "return c;" +
                "\nend" +
                // 执行计算器自加
                "\nc = redis.call('incr',KEYS[1])" +
                "\nif tonumber(c) == 1 then" +
                // 从第一次调用开始限流，设置对应键值的过期
                "\nredis.call('expire',KEYS[1],ARGV[2])" +
                "\nend" +
                "\nreturn c;";
    }


    /**
     * 获取IP
     *
     * @return
     */
    public String getIpAddress() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || Constants.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || Constants.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || Constants.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
