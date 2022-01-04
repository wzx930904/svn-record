package com.sge.service;

import com.sge.advice.BusinessException;
import com.sge.entity.Branch;
import com.sge.entity.MergeInfo;
import com.sge.repository.MergeRepository;
import com.sge.utils.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

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
            merge.setBranchName(branch.getBranchName());
            merge.setMergeBranchName(mergeBranch.getBranchName());
        }));
        return mergeInfos;
    }

    public void save(MergeInfo mergeInfo) {
        validateParam(mergeInfo);
        String mergeDate = DateFormatUtils.CNStringDateTrasfor(mergeInfo.getMergeDate());
        mergeInfo.setMergeDate(mergeDate);
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
        MergeInfo mi = new MergeInfo();
        mi.setBranchId(mergeInfo.getBranchId());
        mi.setMergeBranchId(mergeInfo.getMergeBranchId());
        Example<MergeInfo> example = Example.of(mi);
        long count = mergeRepository.count(example);
        if (count > 0) {
            throw new BusinessException("已合并到该分支");
        }
    }

    public void deleteById(Integer id) {
        boolean flag = mergeRepository.existsById(id);
        if (!flag) {
            throw new BusinessException("记录不存在");
        }
        mergeRepository.deleteById(id);
    }
}
