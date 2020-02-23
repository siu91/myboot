/*
package org.siu.myboot.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.server.entity.po.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

*/
/**
 * @Author Siu
 * @Date 2020/2/15 23:18
 * @Version 0.0.1
 *//*

@Slf4j
@RestController
public class DemoController {
    */
/**
     * @RestController 注解相当于 @ResponseBody ＋ @Controller 合在一起的作用
     * 如果 Web 层的类上使用了 @RestController 注解，就代表这个类中所有的方法都会以 JSON 的形式返回结果，也相当于 JSON 的一种快捷使用方式
     * <p>
     * RequestMapping(name="/getUser", method= RequestMethod.POST)，以 /getUser 的方式去请求，method= RequestMethod.POST 是指只可以使用 Post 的方式去请求，如果使用 Get 的方式去请求的话，则会报 405 不允许访问的错误
     *//*



    @Cacheable(cacheNames = "demo")
    @RequestMapping("/hello")
    public String hello(String name) {
        log.info("没有走缓存");
        return "hello world1345645, " + name;
    }


    @RequestMapping(path = "/getUser", method = RequestMethod.POST)
    public User getUser() {
        User user = new User();
        user.setName("小明");
        user.setAge(12);
        return user;
    }

    */
/**
     * 使用 URL 进行传参，这种形式的传参地址栏会更加美观一些
     *
     * @param name
     * @return
     *//*

    @RequestMapping(value = "get/{name}", method = RequestMethod.GET)
    public String getUsername(@PathVariable String name) {
        return name;
    }


    */
/**
     * 参数校验
     * Spring Boot 的参数校验依赖于 hibernate-validator 来进行
     *
     * @param user
     * @param result
     * @Valid 参数前面添加 @Valid 注解，代表此对象使用了参数校验；
     * BindingResult 参数校验的结果会存储在此对象中，可以根据属性判断是否校验通过，校验不通过可以将错误信息打印出来
     * @Length(min=, max=)	属性（String）	检查字符串长度是否符合范围	列长度会被设到最大值
     * @Max(value=) 属性（以 numeric 或者 string 类型来表示一个数字）	检查值是否小于或等于最大值	对列增加一个检查约束
     * @Min(value=) 属性（以 numeric 或者 string 类型来表示一个数字）	检查值是否大于或等于最小值	对列增加一个检查约束
     * @NotNull 属性    检查值是否非空（not null）	列不为空
     * @Past 属性（date 或 calendar）	检查日期是否是过去时	对列增加一个检查约束
     * @Future 属性（date 或 calendar）	检查日期是否是将来时	无
     * @Pattern(regex="regexp", flag=)	属性（string）	检查属性是否与给定匹配标志的正则表达式相匹配（见 java.util.regex.Pattern ）	无
     * @Range(min=, max=)	属性（以 numeric 或者 string 类型来表示一个数字）	检查值是否在最小和最大值之间（包括临界值）	对列增加一个检查约束
     * @Size(min=, max=)	属性（array，collection，map）	检查元素大小是否在最小和最大值之间（包括临界值）	无
     * @AssertFalse 属性    检查方法的演算结果是否为 false（对以代码方式而不是注解表示的约束很有用）	无
     * @AssertTrue 属性    检查方法的演算结果是否为 true（对以代码方式而不是注解表示的约束很有用）	无
     * @Valid 属性（object）	对关联对象递归进行验证。如果对象是集合或数组，就递归地验证其元素；如果对象是 Map，则递归验证其值元素	无
     * @Email 属性（String）	检查字符串是否符合有效的 email 地址规范	无
     *//*

    @RequestMapping("/saveUser")
    public void saveUser(@Valid User user, BindingResult result) {
        System.out.println("user:" + user);
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                System.out.println(error.getCode() + "-" + error.getDefaultMessage());
            }
        }
    }
}
*/
