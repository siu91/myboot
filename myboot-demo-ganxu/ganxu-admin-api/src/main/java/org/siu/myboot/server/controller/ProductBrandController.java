package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.entity.po.ProductBrand;
import org.siu.myboot.server.service.ProductBrandService;
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
@RequestMapping(value = "/v1/product_brand")
@Slf4j
@Api(tags = {"ProductBrand related API"})
@RestController
public class ProductBrandController {

    @Resource
    private ProductBrandService productBrandService;


    @PostMapping
    @ApiOperation(value = "ProductBrand:CREATE")
    public Result<ProductBrand> create(@RequestBody @Validated(Valid.CREATE.class) ProductBrand params) {
        ProductBrand data = productBrandService.save(params);
        return Result.ok(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "ProductBrand:DELETE")
    public Result<Integer> delete(@PathVariable Long id) {
        productBrandService.delete(id);
        return Result.ok(0);
    }


    @PutMapping()
    @ApiOperation(value = "ProductBrand:UPDATE")
    public Result<ProductBrand> update(@RequestBody @Validated(Valid.UPDATE.class) ProductBrand params) {
        ProductBrand data = productBrandService.save(params);
        return Result.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "ProductBrand:RETRIEVE")
    public Result<ProductBrand> retrieve(@PathVariable Long id) {
        Optional<ProductBrand> data = productBrandService.findById(id);
        return data.map(Result::ok).orElseGet(Result::ok);

    }

    @GetMapping
    @ApiOperation(value = "ProductBrand:PAGE")
    public Result<PageData> page(@RequestBody PageParams<ProductBrand> params) {
        PageData data = productBrandService.getPage(params);
        return Result.ok(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "ProductBrand:LIST")
    public Result<List<ProductBrand>> list(@RequestBody ProductBrand params) {
        List<ProductBrand> data = productBrandService.getList(params, null);
        return Result.ok(data);
    }

}

