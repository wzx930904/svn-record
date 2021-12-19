package com.sge.controller;

import com.sge.entity.BaseResult;
import com.sge.entity.Branch;
import com.sge.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/querybranch.htm/{platformId}")
    @ResponseBody
    public BaseResult<List<Branch>> queryBranch(@PathVariable("platformId") Integer platformId) {
        List<Branch> branches = branchService.findByplatformId(platformId);
        BaseResult<List<Branch>> baseResult = new BaseResult<>();
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
        result.put("name",branch.getName());
        baseResult.setResult(result);
        baseResult.setSuccess(true);
        return baseResult;
    }
}
