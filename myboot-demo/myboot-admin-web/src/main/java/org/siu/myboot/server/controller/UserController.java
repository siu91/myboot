package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.entity.po.User;
import org.siu.myboot.server.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @Author Siu
 * @Date 2020/2/25 15:44
 * @Version 0.0.1
 */
@RequestMapping(value = "/v1/user")
@Slf4j
@Api(tags = {"User related API"})
@RestController
public class UserController {

    @Resource
    private UserService userService;


    @PostMapping
    @ApiOperation(value = "User:CREATE")
    public Result create(@RequestBody @Validated(Valid.CREATE.class) User params) {
        User data = userService.save(params);
        return new Result(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "User:DELETE")
    public Result delete(@PathVariable Long id) {
        userService.delete(id);
        return new Result().success();
    }


    @PutMapping()
    @ApiOperation(value = "User:UPDATE")
    public Result update(@RequestBody @Validated(Valid.UPDATE.class) User params) {
        User data = userService.save(params);
        return new Result(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "User:RETRIEVE")
    public Result retrieve(@PathVariable Long id) {
        Optional<User> data = userService.findById(id);
        return data.map(Result::new).orElseGet(() -> new Result().success());

    }

    @GetMapping
    @ApiOperation(value = "User:PAGE")
    public Result page(@RequestBody PageParams<User> params) {
        PageData data = userService.getPage(params);
        return new Result(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "User:LIST")
    public Result list(@RequestBody User params) {
        List<User> data = userService.getList(params, null);
        return new Result(data);
    }

}
