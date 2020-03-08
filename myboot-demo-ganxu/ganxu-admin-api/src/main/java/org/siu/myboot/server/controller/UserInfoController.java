package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.entity.po.UserInfo;
import org.siu.myboot.server.service.UserInfoService;
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
@RequestMapping(value = "/v1/user_info")
@Slf4j
@Api(tags = {"UserInfo related API"})
@RestController
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;


    @PostMapping
    @ApiOperation(value = "UserInfo:CREATE")
    public Result<UserInfo> create(@RequestBody @Validated(Valid.CREATE.class) UserInfo params) {
        UserInfo data = userInfoService.save(params);
        return Result.ok(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "UserInfo:DELETE")
    public Result<Integer> delete(@PathVariable Long id) {
        userInfoService.delete(id);
        return Result.ok(0);
    }


    @PutMapping()
    @ApiOperation(value = "UserInfo:UPDATE")
    public Result<UserInfo> update(@RequestBody @Validated(Valid.UPDATE.class) UserInfo params) {
        UserInfo data = userInfoService.save(params);
        return Result.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "UserInfo:RETRIEVE")
    public Result<UserInfo> retrieve(@PathVariable Long id) {
        Optional<UserInfo> data = userInfoService.findById(id);
        return data.map(Result::ok).orElseGet(Result::ok);

    }

    @GetMapping
    @ApiOperation(value = "UserInfo:PAGE")
    public Result<PageData> page(@RequestBody PageParams<UserInfo> params) {
        PageData data = userInfoService.getPage(params);
        return Result.ok(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "UserInfo:LIST")
    public Result<List<UserInfo>> list(@RequestBody UserInfo params) {
        List<UserInfo> data = userInfoService.getList(params, null);
        return Result.ok(data);
    }

}

