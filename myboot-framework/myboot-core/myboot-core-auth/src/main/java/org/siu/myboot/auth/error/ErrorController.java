package org.siu.myboot.auth.error;

import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.exception.AuthenticateFail;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


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
public class ErrorController {

    /**
     * 认证、授权异常处理
     *
     * @param msg
     * @throws AuthenticateFail
     */
    @RequestMapping(value = "/auth/error", method = {RequestMethod.POST, RequestMethod.GET})
    public void error(String msg) throws AuthenticateFail {
        log.warn("AuthenticateFail:{}", msg);
        throw new AuthenticateFail(msg);

    }

}
