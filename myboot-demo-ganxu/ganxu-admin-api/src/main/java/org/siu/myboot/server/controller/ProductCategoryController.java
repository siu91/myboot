package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.entity.po.ProductCategory;
import org.siu.myboot.server.service.ProductCategoryService;
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
@RequestMapping(value = "/v1/product_category")
@Slf4j
@Api(tags = {"ProductCategory related API"})
@RestController
public class ProductCategoryController {

    @Resource
    private ProductCategoryService productCategoryService;


    @PostMapping
    @ApiOperation(value = "ProductCategory:CREATE")
    public Result<ProductCategory> create(@RequestBody @Validated(Valid.CREATE.class) ProductCategory params) {
        ProductCategory data = productCategoryService.save(params);
        return Result.ok(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "ProductCategory:DELETE")
    public Result<Integer> delete(@PathVariable Long id) {
        productCategoryService.delete(id);
        return Result.ok(0);
    }


    @PutMapping()
    @ApiOperation(value = "ProductCategory:UPDATE")
    public Result<ProductCategory> update(@RequestBody @Validated(Valid.UPDATE.class) ProductCategory params) {
        ProductCategory data = productCategoryService.save(params);
        return Result.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "ProductCategory:RETRIEVE")
    public Result<ProductCategory> retrieve(@PathVariable Long id) {
        Optional<ProductCategory> data = productCategoryService.findById(id);
        return data.map(Result::ok).orElseGet(Result::ok);

    }

    @GetMapping
    @ApiOperation(value = "ProductCategory:PAGE")
    public Result<PageData> page(@RequestBody PageParams<ProductCategory> params) {
        PageData data = productCategoryService.getPage(params);
        return Result.ok(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "ProductCategory:LIST")
    public Result<List<ProductCategory>> list(@RequestBody ProductCategory params) {
        List<ProductCategory> data = productCategoryService.getList(params, null);
        return Result.ok(data);
    }

}

