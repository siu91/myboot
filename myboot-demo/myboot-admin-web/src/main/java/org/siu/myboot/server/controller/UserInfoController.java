package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.Params;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.entity.po.UserInfo;
import org.siu.myboot.server.service.UserInfoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    public Result create(@RequestBody @Validated(Valid.CREATE.class) UserInfo params) {
        UserInfo data = userInfoService.save(params);
        return new Result(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "UserInfo:DELETE")
    public Result delete(@PathVariable Long id) {
        userInfoService.delete(id);
        return new Result().success();
    }


    @PutMapping()
    @ApiOperation(value = "UserInfo:UPDATE")
    public Result update(@RequestBody @Validated(Valid.UPDATE.class) UserInfo params) {
        UserInfo data = userInfoService.save(params);
        return new Result(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "UserInfo:RETRIEVE")
    public Result retrieve(@PathVariable Long id) {
        Optional<UserInfo> data = userInfoService.findById(id);
        return data.map(Result::new).orElseGet(() -> new Result().success());

    }

    @GetMapping
    @ApiOperation(value = "UserInfo:PAGE")
    public Result page(@RequestBody Params<UserInfo> params) {
        PageData data = userInfoService.getPage(params);
        return new Result(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "UserInfo:LIST")
    public Result list(@RequestBody Params<UserInfo> params) {
        PageData data = userInfoService.getPage(params);
        return new Result(data);
    }

}

