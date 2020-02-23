/*
 * @(#) WebMvcConfig.java 2018/10/11
 *
 * Copyright 2018 CEC(Fujian) Healthcare Big Data Operation Service Co., Ltd. All rights reserved.
 */
package org.siu.myboot.core.web;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.siu.myboot.core.web.handlers.JsonReturnHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author Siu
 * @Date 2020/2/15 23:18
 * @Version 0.0.1
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @PostConstruct
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
    }


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
        list.add(new JsonReturnHandler());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> list) {

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
        /** 1.需要定义一个convert转换消息的对象;*/
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        /** 2.添加fastJson的配置信息，比如：是否要格式化返回的json数据;*/
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteNullListAsEmpty);
        /** 3处理中文乱码问题*/
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        /** 4.在convert中添加配置信息.*/
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        /** 5.将convert添加到converters当中.*/
        list.add(fastJsonHttpMessageConverter);
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
