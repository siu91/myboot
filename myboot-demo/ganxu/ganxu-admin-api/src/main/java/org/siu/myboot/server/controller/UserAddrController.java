package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.entity.po.UserAddr;
import org.siu.myboot.server.service.UserAddrService;
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
@RequestMapping(value = "/v1/admin/user_addr")
@Slf4j
@Api(tags = {"UserAddr related API"})
@RestController
public class UserAddrController {

    @Resource
    private UserAddrService userAddrService;


    @PostMapping
    @ApiOperation(value = "UserAddr:CREATE")
    public Result create(@RequestBody @Validated(Valid.CREATE.class) UserAddr params) {
        UserAddr data = userAddrService.save(params);
        return new Result(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "UserAddr:DELETE")
    public Result delete(@PathVariable Long id) {
        userAddrService.delete(id);
        return new Result().success();
    }


    @PutMapping()
    @ApiOperation(value = "UserAddr:UPDATE")
    public Result update(@RequestBody @Validated(Valid.UPDATE.class) UserAddr params) {
        UserAddr data = userAddrService.save(params);
        return new Result(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "UserAddr:RETRIEVE")
    public Result retrieve(@PathVariable Long id) {
        Optional<UserAddr> data = userAddrService.findById(id);
        return data.map(Result::new).orElseGet(() -> new Result().success());

    }

    @GetMapping
    @ApiOperation(value = "UserAddr:PAGE")
    public Result page(@RequestBody PageParams<UserAddr> params) {
        PageData data = userAddrService.getPage(params);
        return new Result(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "UserAddr:LIST")
    public Result list(@RequestBody UserAddr params) {
        List<UserAddr> data = userAddrService.getList(params, null);
        return new Result(data);
    }

}

