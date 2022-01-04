package com.sge.service;

import com.sge.advice.BusinessException;
import com.sge.entity.SvnRecord;
import com.sge.repository.SvnRecordRepository;
import com.sge.utils.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
 * Created by wzx on 2021/12/8.
 */
@Service
public class SvnRecordService {

    @Autowired
    private SvnRecordRepository svnRecordRepository;
    @Autowired
    private BranchService branchService;

    public void save(SvnRecord svnRecord) {
        validateParam(svnRecord);
        String date = DateFormatUtils.CNStringDateTrasfor(svnRecord.getDate());
        svnRecord.setDate(date);
        svnRecord.setCreateTime(new Date(System.currentTimeMillis()));
        svnRecord.setUpdateTime(new Date(System.currentTimeMillis()));
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

    public Page<SvnRecord> queryByPage(Integer branchId, String fileName, String author, Integer pageSize, Integer pageNum) {
        SvnRecord svnRecord = new SvnRecord();
        svnRecord.setBranchId(branchId);
        if (!StringUtils.isEmpty(fileName)) {
            svnRecord.setFileName(fileName);
        }
        if (!StringUtils.isEmpty(author)) {
            svnRecord.setAuthor(author);
        }
        Sort sort = Sort.by(Sort.Direction.DESC,"createTime");
        Pageable pageable = PageRequest.of(pageNum-1,pageSize,sort);
        Example<SvnRecord> example = Example.of(svnRecord);
        Page<SvnRecord> resultList = svnRecordRepository.findAll(example,pageable);
        return resultList;
    }

    public void deleteByPk(Integer id) {
        boolean flag = svnRecordRepository.existsById(id);
        if (!flag) {
            throw new BusinessException("记录不存在");
        }
        svnRecordRepository.deleteById(id);
    }
}
