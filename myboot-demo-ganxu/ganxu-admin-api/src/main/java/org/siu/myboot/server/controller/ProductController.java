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
@RequestMapping(value = "/v1/admin/product")
@Slf4j
@Api(tags = {"Product related API"})
@RestController
public class ProductController {

    @Resource
    private ProductService productService;


    @PostMapping
    @ApiOperation(value = "Product:CREATE")
    public Result create(@RequestBody @Validated(Valid.CREATE.class) Product params) {
        Product data = productService.save(params);
        return new Result(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "Product:DELETE")
    public Result delete(@PathVariable Long id) {
        productService.delete(id);
        return new Result().success();
    }


    @PutMapping()
    @ApiOperation(value = "Product:UPDATE")
    public Result update(@RequestBody @Validated(Valid.UPDATE.class) Product params) {
        Product data = productService.save(params);
        return new Result(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Product:RETRIEVE")
    public Result retrieve(@PathVariable Long id) {
        Optional<Product> data = productService.findById(id);
        return data.map(Result::new).orElseGet(() -> new Result().success());

    }

    @GetMapping
    @ApiOperation(value = "Product:PAGE")
    public Result page(@RequestBody PageParams<Product> params) {
        PageData data = productService.getPage(params);
        return new Result(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "Product:LIST")
    public Result list(@RequestBody Product params) {
        List<Product> data = productService.getList(params, null);
        return new Result(data);
    }

}

