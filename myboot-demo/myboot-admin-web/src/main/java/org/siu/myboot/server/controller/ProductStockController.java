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
    public Result create(@RequestBody @Validated(Valid.CREATE.class) ProductStock params) {
        ProductStock data = productStockService.save(params);
        return new Result(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "ProductStock:DELETE")
    public Result delete(@PathVariable Long id) {
        productStockService.delete(id);
        return new Result().success();
    }


    @PutMapping()
    @ApiOperation(value = "ProductStock:UPDATE")
    public Result update(@RequestBody @Validated(Valid.UPDATE.class) ProductStock params) {
        ProductStock data = productStockService.save(params);
        return new Result(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "ProductStock:RETRIEVE")
    public Result retrieve(@PathVariable Long id) {
        Optional<ProductStock> data = productStockService.findById(id);
        return data.map(Result::new).orElseGet(() -> new Result().success());

    }

    @GetMapping
    @ApiOperation(value = "ProductStock:PAGE")
    public Result page(@RequestBody PageParams<ProductStock> params) {
        PageData data = productStockService.getPage(params);
        return new Result(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "ProductStock:LIST")
    public Result list(@RequestBody ProductStock params) {
        List<ProductStock> data = productStockService.getList(params, null);
        return new Result(data);
    }

}

