package org.siu.myboot.auth.trace;


import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.constant.Constant;
import org.siu.myboot.core.utils.UUID;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 追踪过滤器
 *
 * @Author Siu
 * @Date 2020/3/4 15:15
 * @Version 0.0.1
 */
@Slf4j
public class TraceFilter extends GenericFilterBean {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        // 追踪ID
        String traceId = httpServletRequest.getHeader(Constant.Trace.TRACE_ID);
        if (StringUtils.isEmpty(traceId)) {
            traceId = UUID.nanoTimeUUID();
        }
        MDC.put(Constant.Trace.TRACE_ID, traceId);

        // 父追踪ID
        String pTraceId = httpServletRequest.getHeader(Constant.Trace.PARENT_TRACE_ID);
        if (!StringUtils.isEmpty(pTraceId)) {
            MDC.put(Constant.Trace.PARENT_TRACE_ID, pTraceId);
        } else {
            MDC.put(Constant.Trace.PARENT_TRACE_ID, Constant.Trace.DEFAULT_PARENT_TRACE_ID);
        }


        filterChain.doFilter(servletRequest, servletResponse);
    }


}


