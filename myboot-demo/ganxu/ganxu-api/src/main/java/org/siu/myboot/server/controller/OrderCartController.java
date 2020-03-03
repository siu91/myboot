package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.entity.po.OrderCart;
import org.siu.myboot.server.service.OrderCartService;
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
@RequestMapping(value = "/v1/order_cart")
@Slf4j
@Api(tags = {"OrderCart related API"})
@RestController
public class OrderCartController {

    @Resource
    private OrderCartService orderCartService;


    @PostMapping
    @ApiOperation(value = "OrderCart:CREATE")
    public Result create(@RequestBody @Validated(Valid.CREATE.class) OrderCart params) {
        OrderCart data = orderCartService.save(params);
        return new Result(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "OrderCart:DELETE")
    public Result delete(@PathVariable Long id) {
        orderCartService.delete(id);
        return new Result().success();
    }


    @PutMapping()
    @ApiOperation(value = "OrderCart:UPDATE")
    public Result update(@RequestBody @Validated(Valid.UPDATE.class) OrderCart params) {
        OrderCart data = orderCartService.save(params);
        return new Result(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "OrderCart:RETRIEVE")
    public Result retrieve(@PathVariable Long id) {
        Optional<OrderCart> data = orderCartService.findById(id);
        return data.map(Result::new).orElseGet(() -> new Result().success());

    }

    @GetMapping
    @ApiOperation(value = "OrderCart:PAGE")
    public Result page(@RequestBody PageParams<OrderCart> params) {
        PageData data = orderCartService.getPage(params);
        return new Result(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "OrderCart:LIST")
    public Result list(@RequestBody OrderCart params) {
        List<OrderCart> data = orderCartService.getList(params, null);
        return new Result(data);
    }

}

