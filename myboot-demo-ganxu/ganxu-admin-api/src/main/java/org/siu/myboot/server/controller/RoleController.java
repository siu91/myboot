package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.entity.po.Role;
import org.siu.myboot.server.service.RoleService;
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
@RequestMapping(value = "/v1/role")
@Slf4j
@Api(tags = {"Role related API"})
@RestController
public class RoleController {

    @Resource
    private RoleService roleService;


    @PostMapping
    @ApiOperation(value = "Role:CREATE")
    public Result<Role> create(@RequestBody @Validated(Valid.CREATE.class) Role params) {
        Role data = roleService.save(params);
        return Result.ok(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "Role:DELETE")
    public Result<Integer> delete(@PathVariable Long id) {
        roleService.delete(id);
        return Result.ok(0);
    }


    @PutMapping()
    @ApiOperation(value = "Role:UPDATE")
    public Result<Role> update(@RequestBody @Validated(Valid.UPDATE.class) Role params) {
        Role data = roleService.save(params);
        return Result.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Role:RETRIEVE")
    public Result<Role> retrieve(@PathVariable Long id) {
        Optional<Role> data = roleService.findById(id);
        return data.map(Result::ok).orElseGet(Result::ok);

    }

    @GetMapping
    @ApiOperation(value = "Role:PAGE")
    public Result<PageData> page(@RequestBody PageParams<Role> params) {
        PageData data = roleService.getPage(params);
        return Result.ok(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "Role:LIST")
    public Result<List<Role>> list(@RequestBody Role params) {
        List<Role> data = roleService.getList(params, null);
        return Result.ok(data);
    }

}

