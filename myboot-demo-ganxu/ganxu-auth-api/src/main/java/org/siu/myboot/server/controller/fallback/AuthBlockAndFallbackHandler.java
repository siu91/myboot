package org.siu.myboot.server.controller.fallback;

import com.alibaba.csp.sentinel.slots.block.AbstractRule;
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
public class AuthBlockAndFallbackHandler {

    /**
     * 熔断处理
     * <p>
     * blockHandler 对应处理 BlockException 的函数名称，可选项。
     * blockHandler 函数访问范围需要是 public
     * 返回类型需要与原方法相匹配
     * 参数类型需要和原方法相匹配并且最后加一个额外的参数，类型为 BlockException。
     * blockHandler 函数默认需要和原方法在同一个类中。
     * 若希望使用其他类的函数，则可以指定 blockHandlerClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
     *
     * @param login
     * @param ex
     * @return
     */
    public static Result<AbstractRule> blockHandler(Login login, BlockException ex) {
        log.error("调用CommonBlockAndFallbackHandler中的blockHandler方法");
        return Result.error(ex.getRule(), "熔断降级处理");
    }

    /**
     * 异常失败处理
     * <p>
     * 用于在抛出异常的时候提供 fallback 处理逻辑。fallback 函数可以针对所有类型的异常（除了exceptionsToIgnore里面排除掉的异常类型）进行处理。
     * fallback 函数签名和位置要求：
     * 1、返回值类型必须与原函数返回值类型一致；
     * 2、方法参数列表需要和原函数一致，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。
     * 3、fallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
     *
     * @param login
     * @return
     */
    public static Result<String> fallbackHandler(Login login, Throwable t) {
        log.error("调用CommonBlockAndFallbackHandler中的fallbackHandler方法");
        return Result.error("异常失败处理:" + t.getMessage());
    }

}
