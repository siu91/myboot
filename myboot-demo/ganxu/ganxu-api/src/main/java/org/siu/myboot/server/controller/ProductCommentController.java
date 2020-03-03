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
    public Result create(@RequestBody @Validated(Valid.CREATE.class) ProductComment params) {
        ProductComment data = productCommentService.save(params);
        return new Result(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "ProductComment:DELETE")
    public Result delete(@PathVariable Long id) {
        productCommentService.delete(id);
        return new Result().success();
    }


    @PutMapping()
    @ApiOperation(value = "ProductComment:UPDATE")
    public Result update(@RequestBody @Validated(Valid.UPDATE.class) ProductComment params) {
        ProductComment data = productCommentService.save(params);
        return new Result(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "ProductComment:RETRIEVE")
    public Result retrieve(@PathVariable Long id) {
        Optional<ProductComment> data = productCommentService.findById(id);
        return data.map(Result::new).orElseGet(() -> new Result().success());

    }

    @GetMapping
    @ApiOperation(value = "ProductComment:PAGE")
    public Result page(@RequestBody PageParams<ProductComment> params) {
        PageData data = productCommentService.getPage(params);
        return new Result(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "ProductComment:LIST")
    public Result list(@RequestBody ProductComment params) {
        List<ProductComment> data = productCommentService.getList(params, null);
        return new Result(data);
    }

}

