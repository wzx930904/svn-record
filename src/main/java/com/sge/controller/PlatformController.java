package com.sge.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.sge.entity.BaseResult;
import com.sge.entity.Branch;
import com.sge.entity.Platform;
import com.sge.entity.SvnRecord;
import com.sge.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wzx on 2021/12/3.
 */
@Controller
@CrossOrigin
@RequestMapping("/svn/platform")
public class PlatformController {

    @Autowired
    private PlatformService platformService;

    /**
     * 查询
     * @return
     */
    @RequestMapping("/query.htm")
    @ResponseBody
    public BaseResult<List<Platform>> query() {
        List<Platform> result = platformService.findAll();
        BaseResult<List<Platform>> baseResult = new BaseResult();
        baseResult.setResult(result);
        baseResult.setSuccess(true);
        return baseResult;
    }

    /**
     * 保存
     * @param platform
     */
    @PostMapping("/save.htm")
    @ResponseBody
    public BaseResult<String> save(Platform platform) {
        platformService.save(platform);
        BaseResult<String> baseResult = new BaseResult<>();
        baseResult.setSuccess(true);
        return baseResult;
    }

    /**
     *修改
     * @param platform
     */
    @PostMapping("/update.htm")
    @ResponseBody
    public BaseResult<String> update(Platform platform) {
        platformService.update(platform);
        BaseResult<String> baseResult = new BaseResult<>();
        baseResult.setSuccess(true);
        return baseResult;
    }

    /**
     * 根据主键删除
     * @param id
     */
    @DeleteMapping("/delete.htm/{id}")
    @ResponseBody
    public BaseResult<String> delete(@PathVariable("id") Integer id) {
        platformService.delete(id);
        BaseResult<String> baseResult = new BaseResult<>();
        baseResult.setSuccess(true);
        return baseResult;
    }

}
