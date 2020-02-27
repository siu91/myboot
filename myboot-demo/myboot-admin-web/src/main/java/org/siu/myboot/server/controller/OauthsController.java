package org.siu.myboot.server.controller;

import org.siu.myboot.core.entity.qo.Params;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.server.entity.po.Oauths;
import org.siu.myboot.server.service.OauthsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author Siu
 * @Date 2020/2/25 15:44
 * @Version 0.0.1
 */
@RequestMapping("/oauths")
@RestController
public class OauthsController {


    @Resource
    private OauthsService oauthsService;

    @GetMapping("/get")
    public Result getPage(@RequestBody Params<Oauths> params) {
        PageData data = oauthsService.getPage(params);
        return new Result(data);
    }


    /*


     */
/**
 * 添加医院信息
 *
 * @param param 创建参数
 * @return
 *//*

    @ApiOperation(value = "医院信息保存", httpMethod = "POST", notes = "保存医院信息信息")
    @RequestMapping(value = "", method = RequestMethod.GET)
    @RequiresPermissions("dcp:hos:add")
    public GlobalResult addDcmDuns(@RequestBody DunsParam param) throws Exception {
        String errorResult = EntityValidateUtil.validateModel(param, EntityValidateUtil.GROUP.ADD);
        if (StringUtils.isNotEmpty(errorResult)) {
            log.error("参数校验失败：{}", errorResult);
            return new GlobalResult(BusinessErrorConsts.RESPONS_ERROR_CODE, errorResult, "");

        }
        DcDuns dcDuns = new DcDuns();
        BeanUtils.copyProperties(param, dcDuns);
        return mDcmDunsService.addDcmDuns(dcDuns);
    }


    */
/**
 * 医院信息分页列表
 *
 * @param param
 * @return
 *//*

    @ApiOperation(value = "医院信息分页列表", httpMethod = "GET", notes = "分页查询医院信息")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public GlobalResult getDcmDunsList(DunsListReq param, BasePageParam pageParam) {
        return mDcmDunsService.getDcDunsFullPage(param, pageParam);
    }



    */
/**
 * 添加医院信息
 *
 * @param param 创建参数
 * @return
 *//*

    @ApiOperation(value = "医院信息保存", httpMethod = "POST", notes = "保存医院信息信息")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @RequiresPermissions("dcp:hos:add")
    public GlobalResult addDcmDuns(@RequestBody DunsParam param) throws Exception {
        String errorResult = EntityValidateUtil.validateModel(param, EntityValidateUtil.GROUP.ADD);
        if (StringUtils.isNotEmpty(errorResult)) {
            log.error("参数校验失败：{}", errorResult);
            return new GlobalResult(BusinessErrorConsts.RESPONS_ERROR_CODE, errorResult, "");

        }
        DcDuns dcDuns = new DcDuns();
        BeanUtils.copyProperties(param, dcDuns);
        return mDcmDunsService.addDcmDuns(dcDuns);
    }

    */
/**
 * 通过id更新医院信息
 *
 * @param dunsId 修改ID
 * @param param  修改参数
 * @return
 *//*

    @ApiOperation(value = "医院信息更新", httpMethod = "PUT", notes = "更新医院信息信息")
    @RequestMapping(value = "/{dunsId}", method = RequestMethod.PUT)
    @RequiresPermissions("dcp:hos:update")
    public GlobalResult updateDcmDunsById(@PathVariable String dunsId, @RequestBody DcDuns param) throws Exception {
        if (Objects.isNull(param)) {
            return new GlobalResult(BusinessErrorConsts.RESPONS_ERROR_CODE, BusinessErrorConsts.RESPONS_ERROR_VALIDATE, "");
        }
        return mDcmDunsService.updateDcmDunsById(dunsId, param);
    }



*/


}
