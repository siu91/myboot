/*
 * @(#) WebMvcConfig.java 2018/10/11
 *
 * Copyright 2018 CEC(Fujian) Healthcare Big Data Operation Service Co., Ltd. All rights reserved.
 */
package org.siu.myboot.core.config.web;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
//import org.siu.myboot.core.config.web.handlers.JsonReturnHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author Siu
 * @Date 2020/2/15 23:18
 * @Version 0.0.1
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    /**
     * TODO  注释以下因为为解决的报错
     * UnsatisfiedDependencyException: Error creating bean with name 'webSecurityConfig': Unsatisfied dependency expressed through method 'setContentNegotationStrategy' parameter 0; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration$EnableWebMvcConfiguration': Unsatisfied dependency expressed through method 'setConfigurers' parameter 0; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'webConfig': Unsatisfied dependency expressed through field 'requestMappingHandlerAdapter'; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'requestMappingHandlerAdapter' defined in class path resource [org/springframework/boot/autoconfigure/web/servlet/WebMvcAutoConfiguration$EnableWebMvcConfiguration.class]: Unsatisfied dependency expressed through method 'requestMappingHandlerAdapter' parameter 0; nested exception is org.springframework.beans.factory.BeanCurrentlyInCreationException: Error creating bean with name 'mvcContentNegotiationManager': Requested bean is currently in creation: Is there an unresolvable circular reference?
     * <p>
     * <p>
     * Related cause: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'requestMappingHandlerAdapter' defined in class path resource [org/springframework/boot/autoconfigure/web/servlet/WebMvcAutoConfiguration$EnableWebMvcConfiguration.class]: Unsatisfied dependency expressed through method 'requestMappingHandlerAdapter' parameter 0; nested exception is org.springframework.beans.factory.BeanCurrentlyInCreationException: Error creating bean with name 'mvcContentNegotiationManager': Requested bean is currently in creation: Is there an unresolvable circular reference?
     * 2020-03-04 19:46:57.272 [main] [pTraceId:] [traceId:] INFO -o.s.orm.jpa.LocalContainerEntityManagerFactoryBean -Closing JPA EntityManagerFactory for persistence unit 'default'
     * 2020-03-04 19:46:57.275 [main] [pTraceId:] [traceId:] INFO -com.zaxxer.hikari.HikariDataSource -MyHikariCP - Shutdown initiated...
     */
    // @Autowired
    //private RequestMappingHandlerAdapter requestMappingHandlerAdapter;


  /*  @PostConstruct
    public void init() {
        // 获取当前 HandlerMethodReturnValueHandler 所有的 Handler 对象
        List<HandlerMethodReturnValueHandler> handlers =
                requestMappingHandlerAdapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> newHandlers = new ArrayList<>(handlers == null ? 1 : handlers.size() + 1);
        // 添加 PropertiesHandlerMethodReturnValueHandler 到集合首位
        newHandlers.add(new JsonReturnHandler());
        // 添加 已注册的 Handler 对象集合
        newHandlers.addAll(handlers);
        // 重新设置 Handler 对象集合
        requestMappingHandlerAdapter.setReturnValueHandlers(newHandlers);
    }*/
    @Override
    public void configurePathMatch(PathMatchConfigurer pathMatchConfigurer) {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer contentNegotiationConfigurer) {

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer asyncSupportConfigurer) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer defaultServletHandlerConfigurer) {

    }

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {

    }

    /**
     * 添加拦截器
     *
     * @param interceptorRegistry
     * @return
     * @throws
     */
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {

    }

    /**
     * 跨域支持
     *
     * @param corsRegistry
     * @return
     * @throws
     */
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
                .maxAge(3600 * 24);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {

    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry) {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> list) {

    }

    /**
     * 返回值统一处理
     *
     * @param list
     * @return
     * @throws
     */
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> list) {
       // list.add(new JsonReturnHandler());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> list) {
         /*

    先把JackSon的消息转换器删除.
    备注: (1)源码分析可知，返回json的过程为:
               Controller调用结束后返回一个数据对象，for循环遍历conventers，找到支持application/json的HttpMessageConverter，然后将返回的数据序列化成json。
               具体参考org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor的writeWithMessageConverters方法
          (2)由于是list结构，我们添加的fastjson在最后。因此必须要将jackson的转换器删除，不然会先匹配上jackson，导致没使用fastjson

   */
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) instanceof MappingJackson2HttpMessageConverter) {
                list.remove(i);
            }
        }

        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

        //自定义fastjson配置
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(
                // 是否输出值为null的字段,默认为false,我们将它打开
                SerializerFeature.WriteMapNullValue,
                // 将Collection类型字段的字段空值输出为[]
                SerializerFeature.WriteNullListAsEmpty,
                // 将字符串类型字段的空值输出为空字符串
                SerializerFeature.WriteNullStringAsEmpty,
                // 将数值类型字段的空值输出为0
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteDateUseDateFormat,
                // 禁用循环引用
                SerializerFeature.DisableCircularReferenceDetect

        );

        fastJsonHttpMessageConverter.setFastJsonConfig(config);

        // 添加支持的MediaTypes;不添加时默认为*/*,也就是默认支持全部
        // 但是MappingJackson2HttpMessageConverter里面支持的MediaTypes为application/json
        // 参考它的做法, fastjson也只添加application/json的MediaType
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        list.add(fastJsonHttpMessageConverter);

    }

    /**
     * 配置消息转换器使用alibaba 开源的 fastjson作为序列化转换器
     *
     * @param
     * @return
     * @throws
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> list) {
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }
}
