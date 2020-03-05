package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.entity.po.Warehouse;
import org.siu.myboot.server.service.WarehouseService;
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
@RequestMapping(value = "/v1/admin/warehouse")
@Slf4j
@Api(tags = {"Warehouse related API"})
@RestController
public class WarehouseController {

    @Resource
    private WarehouseService warehouseService;


    @PostMapping
    @ApiOperation(value = "Warehouse:CREATE")
    public Result create(@RequestBody @Validated(Valid.CREATE.class) Warehouse params) {
        Warehouse data = warehouseService.save(params);
        return new Result(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "Warehouse:DELETE")
    public Result delete(@PathVariable Long id) {
        warehouseService.delete(id);
        return new Result().success();
    }


    @PutMapping()
    @ApiOperation(value = "Warehouse:UPDATE")
    public Result update(@RequestBody @Validated(Valid.UPDATE.class) Warehouse params) {
        Warehouse data = warehouseService.save(params);
        return new Result(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Warehouse:RETRIEVE")
    public Result retrieve(@PathVariable Long id) {
        Optional<Warehouse> data = warehouseService.findById(id);
        return data.map(Result::new).orElseGet(() -> new Result().success());

    }

    @GetMapping
    @ApiOperation(value = "Warehouse:PAGE")
    public Result page(@RequestBody PageParams<Warehouse> params) {
        PageData data = warehouseService.getPage(params);
        return new Result(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "Warehouse:LIST")
    public Result list(@RequestBody Warehouse params) {
        List<Warehouse> data = warehouseService.getList(params, null);
        return new Result(data);
    }

}

