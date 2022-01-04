package com.sge.controller;

import com.sge.entity.BaseResult;
import com.sge.entity.SvnRecord;
import com.sge.service.SvnRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


/**
 * Created by wzx on 2021/12/8.
 */
@RestController
@CrossOrigin
@RequestMapping("/svn/svnrecord")
public class SvnRecordController {

    @Autowired
    private SvnRecordService svnRecordService;

    @GetMapping(value = {"/changelog/{branchId}/fileName/{fileName}/author/{author}/{pageSize}/{pageNum}",
                         "/changelog/{branchId}/{pageSize}/{pageNum}",
                         "/changelog/{branchId}/fileName/{fileName}/{pageSize}/{pageNum}",
                        "/changelog/{branchId}/author/{author}/{pageSize}/{pageNum}" })
    public BaseResult<Page<SvnRecord>> queryByPage(@PathVariable("branchId") Integer branchId,
                  @PathVariable(name="fileName",required = false) String fileName,@PathVariable(name="author",required = false) String author
                  ,@PathVariable("pageSize") Integer pageSize,@PathVariable("pageNum") Integer pageNum) {
        BaseResult<Page<SvnRecord>> result = new BaseResult<>();
        Page<SvnRecord> svnRecords = svnRecordService.queryByPage(branchId,fileName,author,pageSize,pageNum);
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

    @DeleteMapping("/delete.htm/{id}")
    public BaseResult<String> delete(@PathVariable("id") Integer id) {
        BaseResult<String> result = new BaseResult<>();
        svnRecordService.deleteByPk(id);
        result.setSuccess(true);
        return result;
    }
}
