package com.sge.service;

import com.sge.advice.BusinessException;
import com.sge.entity.SvnRecord;
import com.sge.repository.SvnRecordRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wzx on 2021/12/8.
 */
@Service
public class SvnRecordService {

    @Autowired
    private SvnRecordRepository svnRecordRepository;
    @Autowired
    private BranchService branchService;

    public List<SvnRecord> findChangeLogByBranchId(Integer branchId) {
        SvnRecord svnRecord = new SvnRecord();
        svnRecord.setBranchId(branchId);
        Example<SvnRecord> example = Example.of(svnRecord);
        List<SvnRecord> resultList = svnRecordRepository.findAll(example);
        return resultList;
    }

    public void save(SvnRecord svnRecord) {
        validateParam(svnRecord);
        svnRecordRepository.save(svnRecord);
    }

    /**
     * 参数有效性校验
     * @param svnRecord
     */
    private void validateParam(SvnRecord svnRecord) {
        if (svnRecord.getBranchId() == null) {
            throw new BusinessException("分支不能为空");
        }
        branchService.getByPK(svnRecord.getBranchId());
    }
}
