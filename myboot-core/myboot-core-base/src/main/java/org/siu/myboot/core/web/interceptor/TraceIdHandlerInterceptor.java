package org.siu.myboot.core.web.interceptor;

import org.siu.myboot.core.constant.Constants;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 追踪ID
 *
 * @Author Siu
 * @Date 2020/2/28 20:07
 * @Version 0.0.1
 */
@Component("traceIdHandlerInterceptor")
public class TraceIdHandlerInterceptor implements HandlerInterceptor {

    /**
     * 在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理；
     * <p>
     * TODO token/授权/用户等记录也可以放在这里
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 追踪ID
        String traceId = request.getHeader(Constants.TRACE_ID);
        if (StringUtils.isEmpty(traceId)) {
            traceId = UUID.randomUUID().toString().replace("-", "");
        }
        MDC.put(Constants.TRACE_ID, traceId);

        // 父追踪ID
        String pTraceId = request.getHeader(Constants.PARENT_TRACE_ID);
        if (!StringUtils.isEmpty(pTraceId)) {
            MDC.put(Constants.PARENT_TRACE_ID, pTraceId);
        } else {
            MDC.put(Constants.PARENT_TRACE_ID, Constants.DEFAULT_PARENT_TRACE_ID);
        }
        return true;
    }

    /**
     * 在业务处理器处理请求执行完成后，生成视图之前执行。
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用，可用于清理资源等
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.clear();
    }
}
