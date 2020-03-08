package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.entity.po.RoleAuthority;
import org.siu.myboot.server.service.RoleAuthorityService;
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
@RequestMapping(value = "/v1/role_authority")
@Slf4j
@Api(tags = {"RoleAuthority related API"})
@RestController
public class RoleAuthorityController {

    @Resource
    private RoleAuthorityService roleAuthorityService;


    @PostMapping
    @ApiOperation(value = "RoleAuthority:CREATE")
    public Result<RoleAuthority> create(@RequestBody @Validated(Valid.CREATE.class) RoleAuthority params) {
        RoleAuthority data = roleAuthorityService.save(params);
        return Result.ok(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "RoleAuthority:DELETE")
    public Result<Integer> delete(@PathVariable Long id) {
        roleAuthorityService.delete(id);
        return Result.ok(0);
    }


    @PutMapping()
    @ApiOperation(value = "RoleAuthority:UPDATE")
    public Result<RoleAuthority> update(@RequestBody @Validated(Valid.UPDATE.class) RoleAuthority params) {
        RoleAuthority data = roleAuthorityService.save(params);
        return Result.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "RoleAuthority:RETRIEVE")
    public Result<RoleAuthority> retrieve(@PathVariable Long id) {
        Optional<RoleAuthority> data = roleAuthorityService.findById(id);
        return data.map(Result::ok).orElseGet(Result::ok);

    }

    @GetMapping
    @ApiOperation(value = "RoleAuthority:PAGE")
    public Result<PageData> page(@RequestBody PageParams<RoleAuthority> params) {
        PageData data = roleAuthorityService.getPage(params);
        return Result.ok(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "RoleAuthority:LIST")
    public Result<List<RoleAuthority>> list(@RequestBody RoleAuthority params) {
        List<RoleAuthority> data = roleAuthorityService.getList(params, null);
        return Result.ok(data);
    }

}

