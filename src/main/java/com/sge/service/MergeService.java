package com.sge.service;

import com.sge.advice.BusinessException;
import com.sge.entity.Branch;
import com.sge.entity.MergeInfo;
import com.sge.repository.MergeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by wzx on 2021/12/10.
 */
@Service
public class MergeService {

    @Autowired
    private MergeRepository mergeRepository;
    @Autowired
    private BranchService branchService;

    public List<MergeInfo> findByBranchId(Integer branchId) {
        MergeInfo mergeInfo = new MergeInfo();
        mergeInfo.setBranchId(branchId);
        Example<MergeInfo> example = Example.of(mergeInfo);
        List<MergeInfo> mergeInfos = mergeRepository.findAll(example);
        mergeInfos.forEach((merge -> {
            Branch mergeBranch = branchService.getByPK(merge.getMergeBranchId());
            Branch branch = branchService.getByPK(merge.getBranchId());
            merge.setBranchName(branch.getName());
            merge.setMergeBranchName(mergeBranch.getName());
        }));
        return mergeInfos;
    }

    public void save(MergeInfo mergeInfo) {
        validateParam(mergeInfo);
        String mergeDate = mergeInfo.getMergeDate();
        mergeDate = mergeDate.split(Pattern.quote("(中国标准时间)"))[0].replace("GMT+0800","GMT+08:00");
        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss z", Locale.US);
        String formatDate = null;
        try {
            Date date = sdf.parse(mergeDate);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            formatDate = sdf1.format(date);
            mergeInfo.setMergeDate(formatDate);
        } catch (ParseException e) {
           throw new  BusinessException(e.getMessage());
        }
        mergeRepository.save(mergeInfo);
    }

    /**
     * 参数校验
     * @param mergeInfo
     */
    private void validateParam(MergeInfo mergeInfo) {
        if (mergeInfo.getBranchId() == null) {
            throw new BusinessException("分支不能为空");
        }
        if(mergeInfo.getMergeBranchId() == null) {
            throw new BusinessException("请选择合并的分支");
        }
        if (mergeInfo.getMergeBranchId().equals(mergeInfo.getBranchId())) {
            throw new BusinessException("合并的分支不能是同一个分支");
        }
        if (StringUtils.isBlank(mergeInfo.getMessage())) {
            throw new BusinessException("请输入合并信息");
        }
        if (StringUtils.isBlank(mergeInfo.getMergeDate())) {
            throw new BusinessException("请输入合并时间");
        }
    }
}
