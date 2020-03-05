package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.entity.po.UserOauths;
import org.siu.myboot.server.service.UserOauthsService;
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
@RequestMapping(value = "/v1/admin/user_oauths")
@Slf4j
@Api(tags = {"UserOauths related API"})
@RestController
public class UserOauthsController {

    @Resource
    private UserOauthsService userOauthsService;


    @PostMapping
    @ApiOperation(value = "UserOauths:CREATE")
    public Result create(@RequestBody @Validated(Valid.CREATE.class) UserOauths params) {
        UserOauths data = userOauthsService.save(params);
        return new Result(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "UserOauths:DELETE")
    public Result delete(@PathVariable Long id) {
        userOauthsService.delete(id);
        return new Result().success();
    }


    @PutMapping()
    @ApiOperation(value = "UserOauths:UPDATE")
    public Result update(@RequestBody @Validated(Valid.UPDATE.class) UserOauths params) {
        UserOauths data = userOauthsService.save(params);
        return new Result(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "UserOauths:RETRIEVE")
    public Result retrieve(@PathVariable Long id) {
        Optional<UserOauths> data = userOauthsService.findById(id);
        return data.map(Result::new).orElseGet(() -> new Result().success());

    }

    @GetMapping
    @ApiOperation(value = "UserOauths:PAGE")
    public Result page(@RequestBody PageParams<UserOauths> params) {
        PageData data = userOauthsService.getPage(params);
        return new Result(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "UserOauths:LIST")
    public Result list(@RequestBody UserOauths params) {
        List<UserOauths> data = userOauthsService.getList(params, null);
        return new Result(data);
    }

}

