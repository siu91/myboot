package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.entity.po.UserAccountLog;
import org.siu.myboot.server.service.UserAccountLogService;
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
@RequestMapping(value = "/v1/user_account_log")
@Slf4j
@Api(tags = {"UserAccountLog related API"})
@RestController
public class UserAccountLogController {

    @Resource
    private UserAccountLogService userAccountLogService;


    @PostMapping
    @ApiOperation(value = "UserAccountLog:CREATE")
    public Result<UserAccountLog> create(@RequestBody @Validated(Valid.CREATE.class) UserAccountLog params) {
        UserAccountLog data = userAccountLogService.save(params);
        return Result.ok(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "UserAccountLog:DELETE")
    public Result<Integer> delete(@PathVariable Long id) {
        userAccountLogService.delete(id);
        return Result.ok(0);
    }


    @PutMapping()
    @ApiOperation(value = "UserAccountLog:UPDATE")
    public Result<UserAccountLog> update(@RequestBody @Validated(Valid.UPDATE.class) UserAccountLog params) {
        UserAccountLog data = userAccountLogService.save(params);
        return Result.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "UserAccountLog:RETRIEVE")
    public Result<UserAccountLog> retrieve(@PathVariable Long id) {
        Optional<UserAccountLog> data = userAccountLogService.findById(id);
        return data.map(Result::ok).orElseGet(Result::ok);

    }

    @GetMapping
    @ApiOperation(value = "UserAccountLog:PAGE")
    public Result<PageData> page(@RequestBody PageParams<UserAccountLog> params) {
        PageData data = userAccountLogService.getPage(params);
        return Result.ok(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "UserAccountLog:LIST")
    public Result<List<UserAccountLog>> list(@RequestBody UserAccountLog params) {
        List<UserAccountLog> data = userAccountLogService.getList(params, null);
        return Result.ok(data);
    }

}

