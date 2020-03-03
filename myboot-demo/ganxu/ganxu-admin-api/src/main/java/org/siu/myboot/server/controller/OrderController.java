package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.entity.po.Order;
import org.siu.myboot.server.service.OrderService;
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
@RequestMapping(value = "/v1/order")
@Slf4j
@Api(tags = {"Order related API"})
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;


    @PostMapping
    @ApiOperation(value = "Order:CREATE")
    public Result create(@RequestBody @Validated(Valid.CREATE.class) Order params) {
        Order data = orderService.save(params);
        return new Result(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "Order:DELETE")
    public Result delete(@PathVariable Long id) {
        orderService.delete(id);
        return new Result().success();
    }


    @PutMapping()
    @ApiOperation(value = "Order:UPDATE")
    public Result update(@RequestBody @Validated(Valid.UPDATE.class) Order params) {
        Order data = orderService.save(params);
        return new Result(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Order:RETRIEVE")
    public Result retrieve(@PathVariable Long id) {
        Optional<Order> data = orderService.findById(id);
        return data.map(Result::new).orElseGet(() -> new Result().success());

    }

    @GetMapping
    @ApiOperation(value = "Order:PAGE")
    public Result page(@RequestBody PageParams<Order> params) {
        PageData data = orderService.getPage(params);
        return new Result(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "Order:LIST")
    public Result list(@RequestBody Order params) {
        List<Order> data = orderService.getList(params, null);
        return new Result(data);
    }

}

