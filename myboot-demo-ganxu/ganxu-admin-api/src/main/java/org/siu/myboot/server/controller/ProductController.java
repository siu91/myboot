package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.entity.po.Product;
import org.siu.myboot.server.service.ProductService;
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
@RequestMapping(value = "/v1/product")
@Slf4j
@Api(tags = {"Product related API"})
@RestController
public class ProductController {

    @Resource
    private ProductService productService;


    @PostMapping
    @ApiOperation(value = "Product:CREATE")
    public Result<Product> create(@RequestBody @Validated(Valid.CREATE.class) Product params) {
        Product data = productService.save(params);
        return Result.ok(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "Product:DELETE")
    public Result<Integer> delete(@PathVariable Long id) {
        productService.delete(id);
        return Result.ok(0);
    }


    @PutMapping()
    @ApiOperation(value = "Product:UPDATE")
    public Result<Product> update(@RequestBody @Validated(Valid.UPDATE.class) Product params) {
        Product data = productService.save(params);
        return Result.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Product:RETRIEVE")
    public Result<Product> retrieve(@PathVariable Long id) {
        Optional<Product> data = productService.findById(id);
        return data.map(Result::ok).orElseGet(Result::ok);

    }

    @GetMapping
    @ApiOperation(value = "Product:PAGE")
    public Result<PageData> page(@RequestBody PageParams<Product> params) {
        PageData data = productService.getPage(params);
        return Result.ok(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "Product:LIST")
    public Result<List<Product>> list(@RequestBody Product params) {
        List<Product> data = productService.getList(params, null);
        return Result.ok(data);
    }

}

