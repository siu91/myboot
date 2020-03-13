package org.siu.myboot.server.controller.fallback;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.server.entity.qo.Login;


/**
 * 熔断降级处理
 *
 * @Author Siu
 * @Date 2020/3/13 20:21
 * @Version 0.0.1
 */
@Slf4j
public class AuthControllerSentinelFallback {

    public static Result<String> blockHandler(Login login, BlockException ex) {
        log.error("调用AuthControllerSentinelFallback中的blockHandler方法");
        return Result.failed("9999", "熔断降级处理");
    }

    public static Result<String> fallbackHandler(Login login) {
        log.error("调用AuthControllerSentinelFallback中的fallbackHandler方法");
        return Result.error("异常失败处理");
    }

}
