package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.entity.po.ProductComment;
import org.siu.myboot.server.service.ProductCommentService;
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
@RequestMapping(value = "/v1/product_comment")
@Slf4j
@Api(tags = {"ProductComment related API"})
@RestController
public class ProductCommentController {

    @Resource
    private ProductCommentService productCommentService;


    @PostMapping
    @ApiOperation(value = "ProductComment:CREATE")
    public Result<ProductComment> create(@RequestBody @Validated(Valid.CREATE.class) ProductComment params) {
        ProductComment data = productCommentService.save(params);
        return Result.ok(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "ProductComment:DELETE")
    public Result<Integer> delete(@PathVariable Long id) {
        productCommentService.delete(id);
        return Result.ok(0);
    }


    @PutMapping()
    @ApiOperation(value = "ProductComment:UPDATE")
    public Result<ProductComment> update(@RequestBody @Validated(Valid.UPDATE.class) ProductComment params) {
        ProductComment data = productCommentService.save(params);
        return Result.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "ProductComment:RETRIEVE")
    public Result<ProductComment> retrieve(@PathVariable Long id) {
        Optional<ProductComment> data = productCommentService.findById(id);
        return data.map(Result::ok).orElseGet(Result::ok);

    }

    @GetMapping
    @ApiOperation(value = "ProductComment:PAGE")
    public Result<PageData> page(@RequestBody PageParams<ProductComment> params) {
        PageData data = productCommentService.getPage(params);
        return Result.ok(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "ProductComment:LIST")
    public Result<List<ProductComment>> list(@RequestBody ProductComment params) {
        List<ProductComment> data = productCommentService.getList(params, null);
        return Result.ok(data);
    }

}

