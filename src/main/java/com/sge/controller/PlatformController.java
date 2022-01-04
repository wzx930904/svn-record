package com.sge.controller;

import com.sge.entity.BaseResult;
import com.sge.entity.Platform;
import com.sge.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/findall.htm")
    @ResponseBody
    public BaseResult<List<Platform>> findAll() {
        List<Platform> reslut = platformService.findAll();
        BaseResult<List<Platform>> baseResult = new BaseResult<>();
        baseResult.setResult(reslut);
        baseResult.setSuccess(true);
        return baseResult;
    }

    /**
     * 分页查询
     * @return
     */
    @GetMapping(value = {"/query.htm/{pageSize}/{pageNum}/{sysCode}",
                 "/query.htm/{pageSize}/{pageNum}"})
    @ResponseBody
    public BaseResult<Page<Platform>> query(@PathVariable(name ="sysCode",required = false) String sysCode,
                                            @PathVariable("pageSize") int pageSize, @PathVariable("pageNum") int pageNum) {
        Page<Platform> result = platformService.findByPage(sysCode,pageSize,pageNum);
        BaseResult<Page<Platform>> baseResult = new BaseResult();
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
