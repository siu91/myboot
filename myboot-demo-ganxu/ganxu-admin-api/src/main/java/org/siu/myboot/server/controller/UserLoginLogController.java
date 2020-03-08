package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.entity.po.UserLoginLog;
import org.siu.myboot.server.service.UserLoginLogService;
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
@RequestMapping(value = "/v1/user_login_log")
@Slf4j
@Api(tags = {"UserLoginLog related API"})
@RestController
public class UserLoginLogController {

    @Resource
    private UserLoginLogService userLoginLogService;


    @PostMapping
    @ApiOperation(value = "UserLoginLog:CREATE")
    public Result<UserLoginLog> create(@RequestBody @Validated(Valid.CREATE.class) UserLoginLog params) {
        UserLoginLog data = userLoginLogService.save(params);
        return Result.ok(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "UserLoginLog:DELETE")
    public Result<Integer> delete(@PathVariable Long id) {
        userLoginLogService.delete(id);
        return Result.ok(0);
    }


    @PutMapping()
    @ApiOperation(value = "UserLoginLog:UPDATE")
    public Result<UserLoginLog> update(@RequestBody @Validated(Valid.UPDATE.class) UserLoginLog params) {
        UserLoginLog data = userLoginLogService.save(params);
        return Result.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "UserLoginLog:RETRIEVE")
    public Result<UserLoginLog> retrieve(@PathVariable Long id) {
        Optional<UserLoginLog> data = userLoginLogService.findById(id);
        return data.map(Result::ok).orElseGet(Result::ok);

    }

    @GetMapping
    @ApiOperation(value = "UserLoginLog:PAGE")
    public Result<PageData> page(@RequestBody PageParams<UserLoginLog> params) {
        PageData data = userLoginLogService.getPage(params);
        return Result.ok(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "UserLoginLog:LIST")
    public Result<List<UserLoginLog>> list(@RequestBody UserLoginLog params) {
        List<UserLoginLog> data = userLoginLogService.getList(params, null);
        return Result.ok(data);
    }

}

