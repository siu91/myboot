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
@RequestMapping(value = "/v1/admin/user_login_log")
@Slf4j
@Api(tags = {"UserLoginLog related API"})
@RestController
public class UserLoginLogController {

    @Resource
    private UserLoginLogService userLoginLogService;


    @PostMapping
    @ApiOperation(value = "UserLoginLog:CREATE")
    public Result create(@RequestBody @Validated(Valid.CREATE.class) UserLoginLog params) {
        UserLoginLog data = userLoginLogService.save(params);
        return new Result(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "UserLoginLog:DELETE")
    public Result delete(@PathVariable Long id) {
        userLoginLogService.delete(id);
        return new Result().success();
    }


    @PutMapping()
    @ApiOperation(value = "UserLoginLog:UPDATE")
    public Result update(@RequestBody @Validated(Valid.UPDATE.class) UserLoginLog params) {
        UserLoginLog data = userLoginLogService.save(params);
        return new Result(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "UserLoginLog:RETRIEVE")
    public Result retrieve(@PathVariable Long id) {
        Optional<UserLoginLog> data = userLoginLogService.findById(id);
        return data.map(Result::new).orElseGet(() -> new Result().success());

    }

    @GetMapping
    @ApiOperation(value = "UserLoginLog:PAGE")
    public Result page(@RequestBody PageParams<UserLoginLog> params) {
        PageData data = userLoginLogService.getPage(params);
        return new Result(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "UserLoginLog:LIST")
    public Result list(@RequestBody UserLoginLog params) {
        List<UserLoginLog> data = userLoginLogService.getList(params, null);
        return new Result(data);
    }

}

