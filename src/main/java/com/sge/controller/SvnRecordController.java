package com.sge.controller;

import com.sge.entity.BaseResult;
import com.sge.entity.SvnRecord;
import com.sge.service.SvnRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wzx on 2021/12/8.
 */
@RestController
@CrossOrigin
@RequestMapping("/svn/svnrecord")
public class SvnRecordController {

    @Autowired
    private SvnRecordService svnRecordService;

    @GetMapping("/changelog/{branchId}")
    public BaseResult<List<SvnRecord>> findSvnChangeRecordByBranchId(@PathVariable("branchId") Integer branchId) {
        BaseResult<List<SvnRecord>> result = new BaseResult<>();
        List<SvnRecord> svnRecords = svnRecordService.findChangeLogByBranchId(branchId);
        result.setResult(svnRecords);
        result.setSuccess(true);
        return result;
    }

    @PostMapping("/save.htm")
    public BaseResult<String> save(SvnRecord svnRecord) {
        BaseResult<String> baseResult = new BaseResult<>();
        svnRecordService.save(svnRecord);
        baseResult.setSuccess(true);
        return baseResult;
    }
}
