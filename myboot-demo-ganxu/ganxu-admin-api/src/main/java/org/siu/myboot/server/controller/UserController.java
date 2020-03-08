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
    public Result<User> create(@RequestBody @Validated(Valid.CREATE.class) User params) {
        User data = userService.save(params);
        return Result.ok(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "User:DELETE")
    public Result<Integer> delete(@PathVariable Long id) {
        userService.delete(id);
        return Result.ok(0);
    }


    @PutMapping()
    @ApiOperation(value = "User:UPDATE")
    public Result<User> update(@RequestBody @Validated(Valid.UPDATE.class) User params) {
        User data = userService.save(params);
        return Result.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "User:RETRIEVE")
    public Result<User> retrieve(@PathVariable Long id) {
        Optional<User> data = userService.findById(id);
        return data.map(Result::ok).orElseGet(Result::ok);

    }

    @GetMapping
    @ApiOperation(value = "User:PAGE")
    public Result<PageData> page(@RequestBody PageParams<User> params) {
        PageData data = userService.getPage(params);
        return Result.ok(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "User:LIST")
    public Result<List<User>> list(@RequestBody User params) {
        List<User> data = userService.getList(params, null);
        return Result.ok(data);
    }

}

