package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.entity.po.UserPointLog;
import org.siu.myboot.server.service.UserPointLogService;
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
@RequestMapping(value = "/v1/user_point_log")
@Slf4j
@Api(tags = {"UserPointLog related API"})
@RestController
public class UserPointLogController {

    @Resource
    private UserPointLogService userPointLogService;


    @PostMapping
    @ApiOperation(value = "UserPointLog:CREATE")
    public Result<UserPointLog> create(@RequestBody @Validated(Valid.CREATE.class) UserPointLog params) {
        UserPointLog data = userPointLogService.save(params);
        return Result.ok(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "UserPointLog:DELETE")
    public Result<Integer> delete(@PathVariable Long id) {
        userPointLogService.delete(id);
        return Result.ok(0);
    }


    @PutMapping()
    @ApiOperation(value = "UserPointLog:UPDATE")
    public Result<UserPointLog> update(@RequestBody @Validated(Valid.UPDATE.class) UserPointLog params) {
        UserPointLog data = userPointLogService.save(params);
        return Result.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "UserPointLog:RETRIEVE")
    public Result<UserPointLog> retrieve(@PathVariable Long id) {
        Optional<UserPointLog> data = userPointLogService.findById(id);
        return data.map(Result::ok).orElseGet(Result::ok);

    }

    @GetMapping
    @ApiOperation(value = "UserPointLog:PAGE")
    public Result<PageData> page(@RequestBody PageParams<UserPointLog> params) {
        PageData data = userPointLogService.getPage(params);
        return Result.ok(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "UserPointLog:LIST")
    public Result<List<UserPointLog>> list(@RequestBody UserPointLog params) {
        List<UserPointLog> data = userPointLogService.getList(params, null);
        return Result.ok(data);
    }

}

