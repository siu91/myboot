package org.siu.myboot.core.web.limiting;


import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.siu.myboot.core.constant.Constant;
import org.siu.myboot.core.exception.OverLimitException;
import org.siu.myboot.core.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
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
@Component
@Aspect
public class LimitingAspect {

    @Autowired
    private RedisTemplate<String, Serializable> limitRedisTemplate;


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
    public Object interceptor(ProceedingJoinPoint pjp) throws OverLimitException {
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
        if (StringUtils.isEmpty(limitAnnotation.key()) && StringUtils.isEmpty(limitAnnotation.prefix())) {
            limitType = LimitingType.IP;
        }
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
            if (count != null && count.intValue() <= limitCount) {
                return pjp.proceed();
            } else {
                throw new OverLimitException("超出流量-当前流量[" + count + "],限流[" + limitCount + "],限制[每" + limitPeriod + "秒" + limitCount + "个请求]");
            }
        } catch (Throwable e) {
            if (e instanceof OverLimitException) {
                throw new OverLimitException(e.getMessage());
            } else {
                throw new RuntimeException("server exception");
            }

        }
    }

    /**
     * 限流Lua脚本
     *
     * @return lua脚本
     */
    public String limitingLuaScript() {
        StringBuilder lua = new StringBuilder();
        lua.append("local c");
        lua.append("\nc = redis.call('get',KEYS[1])");
        // 调用不超过最大值，则直接返回
        lua.append("\nif c and tonumber(c) > tonumber(ARGV[1]) then");
        lua.append("\nreturn tonumber(c);");
        lua.append("\nend");
        // 执行计算器自加
        lua.append("\nc = redis.call('incr',KEYS[1])");
        lua.append("\nif tonumber(c) == 1 then");
        // 从第一次调用开始限流，设置对应键值的过期
        lua.append("\nredis.call('expire',KEYS[1],ARGV[2])");
        lua.append("\nend");
        lua.append("\nreturn tonumber(c);");
        return lua.toString();
    }


    /**
     * 获取IP
     *
     * @return
     */
    public String getIpAddress() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || Constant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || Constant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || Constant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
