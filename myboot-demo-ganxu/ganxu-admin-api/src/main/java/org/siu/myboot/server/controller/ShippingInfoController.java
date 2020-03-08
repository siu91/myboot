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
    public Result<ShippingInfo> create(@RequestBody @Validated(Valid.CREATE.class) ShippingInfo params) {
        ShippingInfo data = shippingInfoService.save(params);
        return Result.ok(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "ShippingInfo:DELETE")
    public Result<Integer> delete(@PathVariable Long id) {
        shippingInfoService.delete(id);
        return Result.ok(0);
    }


    @PutMapping()
    @ApiOperation(value = "ShippingInfo:UPDATE")
    public Result<ShippingInfo> update(@RequestBody @Validated(Valid.UPDATE.class) ShippingInfo params) {
        ShippingInfo data = shippingInfoService.save(params);
        return Result.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "ShippingInfo:RETRIEVE")
    public Result<ShippingInfo> retrieve(@PathVariable Long id) {
        Optional<ShippingInfo> data = shippingInfoService.findById(id);
        return data.map(Result::ok).orElseGet(Result::ok);

    }

    @GetMapping
    @ApiOperation(value = "ShippingInfo:PAGE")
    public Result<PageData> page(@RequestBody PageParams<ShippingInfo> params) {
        PageData data = shippingInfoService.getPage(params);
        return Result.ok(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "ShippingInfo:LIST")
    public Result<List<ShippingInfo>> list(@RequestBody ShippingInfo params) {
        List<ShippingInfo> data = shippingInfoService.getList(params, null);
        return Result.ok(data);
    }

}

