package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.entity.po.UserLevel;
import org.siu.myboot.server.service.UserLevelService;
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
@RequestMapping(value = "/v1/user_level")
@Slf4j
@Api(tags = {"UserLevel related API"})
@RestController
public class UserLevelController {

    @Resource
    private UserLevelService userLevelService;


    @PostMapping
    @ApiOperation(value = "UserLevel:CREATE")
    public Result create(@RequestBody @Validated(Valid.CREATE.class) UserLevel params) {
        UserLevel data = userLevelService.save(params);
        return new Result(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "UserLevel:DELETE")
    public Result delete(@PathVariable Long id) {
        userLevelService.delete(id);
        return new Result().success();
    }


    @PutMapping()
    @ApiOperation(value = "UserLevel:UPDATE")
    public Result update(@RequestBody @Validated(Valid.UPDATE.class) UserLevel params) {
        UserLevel data = userLevelService.save(params);
        return new Result(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "UserLevel:RETRIEVE")
    public Result retrieve(@PathVariable Long id) {
        Optional<UserLevel> data = userLevelService.findById(id);
        return data.map(Result::new).orElseGet(() -> new Result().success());

    }

    @GetMapping
    @ApiOperation(value = "UserLevel:PAGE")
    public Result page(@RequestBody PageParams<UserLevel> params) {
        PageData data = userLevelService.getPage(params);
        return new Result(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "UserLevel:LIST")
    public Result list(@RequestBody UserLevel params) {
        List<UserLevel> data = userLevelService.getList(params, null);
        return new Result(data);
    }

}

