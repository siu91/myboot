package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.valid.Valid;
import org.siu.myboot.server.entity.po.ShippingInfo;
import org.siu.myboot.server.service.ShippingInfoService;
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
@RequestMapping(value = "/v1/shipping_info")
@Slf4j
@Api(tags = {"ShippingInfo related API"})
@RestController
public class ShippingInfoController {

    @Resource
    private ShippingInfoService shippingInfoService;


    @PostMapping
    @ApiOperation(value = "ShippingInfo:CREATE")
    public Result create(@RequestBody @Validated(Valid.CREATE.class) ShippingInfo params) {
        ShippingInfo data = shippingInfoService.save(params);
        return new Result(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "ShippingInfo:DELETE")
    public Result delete(@PathVariable Long id) {
        shippingInfoService.delete(id);
        return new Result().success();
    }


    @PutMapping()
    @ApiOperation(value = "ShippingInfo:UPDATE")
    public Result update(@RequestBody @Validated(Valid.UPDATE.class) ShippingInfo params) {
        ShippingInfo data = shippingInfoService.save(params);
        return new Result(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "ShippingInfo:RETRIEVE")
    public Result retrieve(@PathVariable Long id) {
        Optional<ShippingInfo> data = shippingInfoService.findById(id);
        return data.map(Result::new).orElseGet(() -> new Result().success());

    }

    @GetMapping
    @ApiOperation(value = "ShippingInfo:PAGE")
    public Result page(@RequestBody PageParams<ShippingInfo> params) {
        PageData data = shippingInfoService.getPage(params);
        return new Result(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "ShippingInfo:LIST")
    public Result list(@RequestBody ShippingInfo params) {
        List<ShippingInfo> data = shippingInfoService.getList(params, null);
        return new Result(data);
    }

}

