package org.siu.myboot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.qo.Params;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.server.entity.po.Oauths;
import org.siu.myboot.server.service.OauthsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @Author Siu
 * @Date 2020/2/25 15:44
 * @Version 0.0.1
 */
@RequestMapping(value = "/v1.0/oauths")
@Slf4j
@Api(tags = {"Oauths 鐩稿叧鎺ュ彛"})
@RestController
public class OauthsController {

    @Resource
    private OauthsService oauthsService;


    @PostMapping
    @ApiOperation(value = "add Oauths")
    public Result add(@RequestBody Oauths params) {
        Oauths data = oauthsService.save(params);
        return new Result(data);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete Oauths")
    public Result delete(@PathVariable Long id) {
        oauthsService.delete(id);
        return new Result().success();
    }


    @PutMapping()
    @ApiOperation(value = "update Oauths")
    public Result update(Oauths params) {
        Oauths data = oauthsService.save(params);
        return new Result(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "query Oauths by id")
    public Result query(@PathVariable Long id) {
        Optional<Oauths> data = oauthsService.findById(id);
        return data.map(Result::new).orElseGet(() -> new Result().success());

    }

    @GetMapping
    @ApiOperation(value = "Oauths query page")
    public Result page(@RequestBody Params<Oauths> params) {
        PageData data = oauthsService.getPage(params);
        return new Result(data);
    }


    @GetMapping("/list")
    @ApiOperation(value = "Oauths query list")
    public Result list(@RequestBody Params<Oauths> params) {
        PageData data = oauthsService.getPage(params);
        return new Result(data);
    }

}

