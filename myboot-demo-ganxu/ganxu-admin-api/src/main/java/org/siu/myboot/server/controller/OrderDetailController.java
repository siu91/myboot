package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.entity.po.OrderDetail;
import org.siu.myboot.server.service.OrderDetailService;
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
@RequestMapping(value = "/v1/order_detail")
@Slf4j
@Api(tags = {"OrderDetail related API"})
@RestController
public class OrderDetailController {

    @Resource
    private OrderDetailService orderDetailService;


    @PostMapping
    @ApiOperation(value = "OrderDetail:CREATE")
    public Result<OrderDetail> create(@RequestBody @Validated(Valid.CREATE.class) OrderDetail params) {
        OrderDetail data = orderDetailService.save(params);
        return Result.ok(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "OrderDetail:DELETE")
    public Result<Integer> delete(@PathVariable Long id) {
        orderDetailService.delete(id);
        return Result.ok(0);
    }


    @PutMapping()
    @ApiOperation(value = "OrderDetail:UPDATE")
    public Result<OrderDetail> update(@RequestBody @Validated(Valid.UPDATE.class) OrderDetail params) {
        OrderDetail data = orderDetailService.save(params);
        return Result.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "OrderDetail:RETRIEVE")
    public Result<OrderDetail> retrieve(@PathVariable Long id) {
        Optional<OrderDetail> data = orderDetailService.findById(id);
        return data.map(Result::ok).orElseGet(Result::ok);

    }

    @GetMapping
    @ApiOperation(value = "OrderDetail:PAGE")
    public Result<PageData> page(@RequestBody PageParams<OrderDetail> params) {
        PageData data = orderDetailService.getPage(params);
        return Result.ok(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "OrderDetail:LIST")
    public Result<List<OrderDetail>> list(@RequestBody OrderDetail params) {
        List<OrderDetail> data = orderDetailService.getList(params, null);
        return Result.ok(data);
    }

}

