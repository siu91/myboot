package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.entity.po.UserRole;
import org.siu.myboot.server.service.UserRoleService;
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
@RequestMapping(value = "/v1/user_role")
@Slf4j
@Api(tags = {"UserRole related API"})
@RestController
public class UserRoleController {

    @Resource
    private UserRoleService userRoleService;


    @PostMapping
    @ApiOperation(value = "UserRole:CREATE")
    public Result<UserRole> create(@RequestBody @Validated(Valid.CREATE.class) UserRole params) {
        UserRole data = userRoleService.save(params);
        return Result.ok(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "UserRole:DELETE")
    public Result<Integer> delete(@PathVariable Long id) {
        userRoleService.delete(id);
        return Result.ok(0);
    }


    @PutMapping()
    @ApiOperation(value = "UserRole:UPDATE")
    public Result<UserRole> update(@RequestBody @Validated(Valid.UPDATE.class) UserRole params) {
        UserRole data = userRoleService.save(params);
        return Result.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "UserRole:RETRIEVE")
    public Result<UserRole> retrieve(@PathVariable Long id) {
        Optional<UserRole> data = userRoleService.findById(id);
        return data.map(Result::ok).orElseGet(Result::ok);

    }

    @GetMapping
    @ApiOperation(value = "UserRole:PAGE")
    public Result<PageData> page(@RequestBody PageParams<UserRole> params) {
        PageData data = userRoleService.getPage(params);
        return Result.ok(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "UserRole:LIST")
    public Result<List<UserRole>> list(@RequestBody UserRole params) {
        List<UserRole> data = userRoleService.getList(params, null);
        return Result.ok(data);
    }

}

