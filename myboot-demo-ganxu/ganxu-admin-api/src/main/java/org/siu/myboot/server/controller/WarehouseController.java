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
@RequestMapping(value = "/v1/warehouse")
@Slf4j
@Api(tags = {"Warehouse related API"})
@RestController
public class WarehouseController {

    @Resource
    private WarehouseService warehouseService;


    @PostMapping
    @ApiOperation(value = "Warehouse:CREATE")
    public Result<Warehouse> create(@RequestBody @Validated(Valid.CREATE.class) Warehouse params) {
        Warehouse data = warehouseService.save(params);
        return Result.ok(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "Warehouse:DELETE")
    public Result<Integer> delete(@PathVariable Long id) {
        warehouseService.delete(id);
        return Result.ok(0);
    }


    @PutMapping()
    @ApiOperation(value = "Warehouse:UPDATE")
    public Result<Warehouse> update(@RequestBody @Validated(Valid.UPDATE.class) Warehouse params) {
        Warehouse data = warehouseService.save(params);
        return Result.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Warehouse:RETRIEVE")
    public Result<Warehouse> retrieve(@PathVariable Long id) {
        Optional<Warehouse> data = warehouseService.findById(id);
        return data.map(Result::ok).orElseGet(Result::ok);

    }

    @GetMapping
    @ApiOperation(value = "Warehouse:PAGE")
    public Result<PageData> page(@RequestBody PageParams<Warehouse> params) {
        PageData data = warehouseService.getPage(params);
        return Result.ok(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "Warehouse:LIST")
    public Result<List<Warehouse>> list(@RequestBody Warehouse params) {
        List<Warehouse> data = warehouseService.getList(params, null);
        return Result.ok(data);
    }

}

