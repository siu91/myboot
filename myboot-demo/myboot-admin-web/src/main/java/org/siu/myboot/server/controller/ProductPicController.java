package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.entity.po.ProductPic;
import org.siu.myboot.server.service.ProductPicService;
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
@RequestMapping(value = "/v1/product_pic")
@Slf4j
@Api(tags = {"ProductPic related API"})
@RestController
public class ProductPicController {

    @Resource
    private ProductPicService productPicService;


    @PostMapping
    @ApiOperation(value = "ProductPic:CREATE")
    public Result create(@RequestBody @Validated(Valid.CREATE.class) ProductPic params) {
        ProductPic data = productPicService.save(params);
        return new Result(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "ProductPic:DELETE")
    public Result delete(@PathVariable Long id) {
        productPicService.delete(id);
        return new Result().success();
    }


    @PutMapping()
    @ApiOperation(value = "ProductPic:UPDATE")
    public Result update(@RequestBody @Validated(Valid.UPDATE.class) ProductPic params) {
        ProductPic data = productPicService.save(params);
        return new Result(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "ProductPic:RETRIEVE")
    public Result retrieve(@PathVariable Long id) {
        Optional<ProductPic> data = productPicService.findById(id);
        return data.map(Result::new).orElseGet(() -> new Result().success());

    }

    @GetMapping
    @ApiOperation(value = "ProductPic:PAGE")
    public Result page(@RequestBody PageParams<ProductPic> params) {
        PageData data = productPicService.getPage(params);
        return new Result(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "ProductPic:LIST")
    public Result list(@RequestBody ProductPic params) {
        List<ProductPic> data = productPicService.getList(params, null);
        return new Result(data);
    }

}

