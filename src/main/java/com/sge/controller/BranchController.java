package com.sge.controller;

import com.sge.entity.BaseResult;
import com.sge.entity.Branch;
import com.sge.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wzx on 2021/12/7.
 */
@Controller
@CrossOrigin
@RequestMapping("/svn/branch")
public class BranchController {

    @Autowired
    private BranchService branchService;

    /**
     * 根据系统id查询系统分支信息
     * @param platformId
     * @return
     */
    @GetMapping(value = {"/querybranch.htm/{platformId}/{pageNum}/{pageSize}",
                          "/querybranch.htm/{pageNum}/{pageSize}"})
    @ResponseBody
    public BaseResult<Page<Branch>> queryBranch(@PathVariable(name="platformId",required = false) Integer platformId,
                                        @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        Page<Branch> branches = branchService.findByPage(platformId,pageNum,pageSize);
        BaseResult<Page<Branch>> baseResult = new BaseResult<>();
        baseResult.setSuccess(true);
        baseResult.setResult(branches);
        return baseResult;
    }

    @PostMapping("/save.htm")
    @ResponseBody
    public BaseResult<String> save(Branch branch) {
        branchService.save(branch);
        BaseResult<String> baseResult = new BaseResult<>();
        baseResult.setSuccess(true);
        return  baseResult;
    }

    @PostMapping("/update.htm")
    @ResponseBody
    public BaseResult<String> update(Branch branch) {
        branchService.update(branch);
        BaseResult<String> baseResult = new BaseResult<>();
        baseResult.setSuccess(true);
        return  baseResult;
    }

    @DeleteMapping("/delete.htm/{id}")
    @ResponseBody
    public BaseResult<String> delete(@PathVariable("id") Integer id) {
        branchService.delete(id);
        BaseResult<String> baseResult = new BaseResult<>();
        baseResult.setSuccess(true);
        return  baseResult;
    }

    @GetMapping("/getBranchsAndName.htm/{platformId}/{bId}")
    @ResponseBody
    public BaseResult<Map<String,Object>> getBranchsAndBranchNameByParams(
            @PathVariable("platformId") Integer platformId,@PathVariable("bId") Integer bId) {
        BaseResult<Map<String,Object>> baseResult = new BaseResult<>();
        List<Branch> branches = branchService.findByplatformId(platformId);
        Branch branch = branchService.getByPK(bId);
        Map<String,Object> result = new HashMap<>();
        result.put("branchs",branches);
        result.put("name",branch.getBranchName());
        baseResult.setResult(result);
        baseResult.setSuccess(true);
        return baseResult;
    }
}
