package com.sge.controller;

import com.sge.entity.BaseResult;
import com.sge.entity.MergeInfo;
import com.sge.service.MergeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wzx on 2021/12/10.
 */
@RestController
@CrossOrigin
@RequestMapping("/svn/merge")
public class MergeController {

    @Autowired
    private MergeService mergeService;

    @GetMapping("/query.htm/{branchId}")
    public BaseResult<List<MergeInfo>> findByBranchId(@PathVariable("branchId") Integer branchId) {
        BaseResult<List<MergeInfo>> baseResult = new BaseResult<>();
        List<MergeInfo> mergeInfos = mergeService.findByBranchId(branchId);
        baseResult.setSuccess(true);
        baseResult.setResult(mergeInfos);
        return baseResult;
    }

    @PostMapping("/save.htm")
    public BaseResult<String> save(MergeInfo mergeInfo) {
      BaseResult<String> baseResult = new BaseResult<>();
      mergeService.save(mergeInfo);
      baseResult.setSuccess(true);
      return baseResult;
    }
}
