package org.siu.myboot.core.web.interceptor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author Siu
 * @Date 2020/2/16 14:55
 * @Version 0.0.1
 */
public class DemoFilter1 implements Filter {
    /**
     * 自定义 Filter
     * Filter 也称之为过滤器，可以在前端拦截所有用户的请求，可以认为是 Servlet 的一种加强版
     * Web 开发人员通过 Filter 技术，对 Web 服务器管理的所有 Web 资源，例如 JSP、Servlet、静态图片文件或静态 HTML 文件等进行拦截，从而实现一些特殊的功能。例如，实现 URL 级别的权限访问控制、过滤敏感词汇、排除有 XSS 威胁的字符、记录请求日志等一些高级功能。
     * <p>
     * Spring Boot 内置了一些 Filter，比如，处理编码的 OrderedCharacterEncodingFilter 和请求转化的 HiddenHttpMethodFilter，也支持根据我们的需求来可以自定义 Filter。
     * <p>
     * 自定义 Filter 有两种实现方式：
     * 第一种是使用 @WebFilter
     * 第二种是使用 FilterRegistrationBean，经过实践之后发现使用 @WebFilter 自定义的过滤器优先级顺序不能生效，因此推荐使用第二个方案
     */

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("this is DemoFilter1,url :" + request.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }


}
