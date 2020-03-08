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
@RequestMapping(value = "/v1/admin/order_detail")
@Slf4j
@Api(tags = {"OrderDetail related API"})
@RestController
public class OrderDetailController {

    @Resource
    private OrderDetailService orderDetailService;


    @PostMapping
    @ApiOperation(value = "OrderDetail:CREATE")
    public Result create(@RequestBody @Validated(Valid.CREATE.class) OrderDetail params) {
        OrderDetail data = orderDetailService.save(params);
        return new Result(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "OrderDetail:DELETE")
    public Result delete(@PathVariable Long id) {
        orderDetailService.delete(id);
        return new Result().success();
    }


    @PutMapping()
    @ApiOperation(value = "OrderDetail:UPDATE")
    public Result update(@RequestBody @Validated(Valid.UPDATE.class) OrderDetail params) {
        OrderDetail data = orderDetailService.save(params);
        return new Result(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "OrderDetail:RETRIEVE")
    public Result retrieve(@PathVariable Long id) {
        Optional<OrderDetail> data = orderDetailService.findById(id);
        return data.map(Result::new).orElseGet(() -> new Result().success());

    }

    @GetMapping
    @ApiOperation(value = "OrderDetail:PAGE")
    public Result page(@RequestBody PageParams<OrderDetail> params) {
        PageData data = orderDetailService.getPage(params);
        return new Result(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "OrderDetail:LIST")
    public Result list(@RequestBody OrderDetail params) {
        List<OrderDetail> data = orderDetailService.getList(params, null);
        return new Result(data);
    }

}

