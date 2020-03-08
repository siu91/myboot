package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.entity.po.ProductStock;
import org.siu.myboot.server.service.ProductStockService;
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
@RequestMapping(value = "/v1/product_stock")
@Slf4j
@Api(tags = {"ProductStock related API"})
@RestController
public class ProductStockController {

    @Resource
    private ProductStockService productStockService;


    @PostMapping
    @ApiOperation(value = "ProductStock:CREATE")
    public Result<ProductStock> create(@RequestBody @Validated(Valid.CREATE.class) ProductStock params) {
        ProductStock data = productStockService.save(params);
        return Result.ok(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "ProductStock:DELETE")
    public Result<Integer> delete(@PathVariable Long id) {
        productStockService.delete(id);
        return Result.ok(0);
    }


    @PutMapping()
    @ApiOperation(value = "ProductStock:UPDATE")
    public Result<ProductStock> update(@RequestBody @Validated(Valid.UPDATE.class) ProductStock params) {
        ProductStock data = productStockService.save(params);
        return Result.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "ProductStock:RETRIEVE")
    public Result<ProductStock> retrieve(@PathVariable Long id) {
        Optional<ProductStock> data = productStockService.findById(id);
        return data.map(Result::ok).orElseGet(Result::ok);

    }

    @GetMapping
    @ApiOperation(value = "ProductStock:PAGE")
    public Result<PageData> page(@RequestBody PageParams<ProductStock> params) {
        PageData data = productStockService.getPage(params);
        return Result.ok(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "ProductStock:LIST")
    public Result<List<ProductStock>> list(@RequestBody ProductStock params) {
        List<ProductStock> data = productStockService.getList(params, null);
        return Result.ok(data);
    }

}

