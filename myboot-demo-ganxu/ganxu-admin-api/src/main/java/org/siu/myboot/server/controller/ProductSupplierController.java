package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.entity.po.ProductSupplier;
import org.siu.myboot.server.service.ProductSupplierService;
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
@RequestMapping(value = "/v1/product_supplier")
@Slf4j
@Api(tags = {"ProductSupplier related API"})
@RestController
public class ProductSupplierController {

    @Resource
    private ProductSupplierService productSupplierService;


    @PostMapping
    @ApiOperation(value = "ProductSupplier:CREATE")
    public Result<ProductSupplier> create(@RequestBody @Validated(Valid.CREATE.class) ProductSupplier params) {
        ProductSupplier data = productSupplierService.save(params);
        return Result.ok(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "ProductSupplier:DELETE")
    public Result<Integer> delete(@PathVariable Long id) {
        productSupplierService.delete(id);
        return Result.ok(0);
    }


    @PutMapping()
    @ApiOperation(value = "ProductSupplier:UPDATE")
    public Result<ProductSupplier> update(@RequestBody @Validated(Valid.UPDATE.class) ProductSupplier params) {
        ProductSupplier data = productSupplierService.save(params);
        return Result.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "ProductSupplier:RETRIEVE")
    public Result<ProductSupplier> retrieve(@PathVariable Long id) {
        Optional<ProductSupplier> data = productSupplierService.findById(id);
        return data.map(Result::ok).orElseGet(Result::ok);

    }

    @GetMapping
    @ApiOperation(value = "ProductSupplier:PAGE")
    public Result<PageData> page(@RequestBody PageParams<ProductSupplier> params) {
        PageData data = productSupplierService.getPage(params);
        return Result.ok(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "ProductSupplier:LIST")
    public Result<List<ProductSupplier>> list(@RequestBody ProductSupplier params) {
        List<ProductSupplier> data = productSupplierService.getList(params, null);
        return Result.ok(data);
    }

}

