package com.sge.service;

import com.sge.advice.BusinessException;
import com.sge.entity.Branch;
import com.sge.entity.SvnRecord;
import com.sge.repository.BranchRepository;
import com.sge.repository.SvnRecordRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Created by wzx on 2021/12/7.
 */
@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private SvnRecordRepository svnRecordRepository;

    /**
     * 根据系统id查询分支
     * @param platformId 系统id
     * @return
     */
    public List<Branch> findByplatformId(Integer platformId) {
        Branch branch = new Branch();
        branch.setPlatformId(platformId);
        Example<Branch> example = Example.of(branch);
        List<Branch> branches = branchRepository.findAll(example);
        return branches;
    }

    /**
     * 保存
     * @param branch
     */
    public void save(Branch branch) {
        vaildateParam(branch);
        String createDate = branch.getCreateDate();
        createDate = createDate.split(Pattern.quote("(中国标准时间)"))[0].replace("GMT+0800","GMT+08:00");
        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss z", Locale.US);
        String formatDate = null;
        try {
            Date date = sdf.parse(createDate);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            formatDate = sdf1.format(date);
            branch.setCreateDate(formatDate);
        } catch (ParseException e) {
            throw new BusinessException(e.getMessage());
        }
        branchRepository.save(branch);
    }

    /**
     * 参数有效性检验
     * @param branch
     */
    private void vaildateParam(Branch branch) {
        if (StringUtils.isBlank(branch.getName())) {
            throw new BusinessException("请输入版本名称");
        }
        if (StringUtils.isBlank(branch.getCreateDate())) {
            throw new BusinessException("请输入创建日期");
        }
    }

    /**
     * 更新
     * @param branch
     */
    public void update(Branch branch) {
        boolean flag = branchRepository.existsById(branch.getId());
        if (!flag) {
            throw new BusinessException("该记录不存在");
        }
        branchRepository.save(branch);
    }

    public void delete(Integer id) {
        boolean flag = branchRepository.existsById(id);
        if (!flag) {
            throw new BusinessException("该记录不存在");
        }
        branchRepository.deleteById(id);
    }

    public Branch getByPK(Integer id) {
        Optional<Branch> optional = branchRepository.findById(id);
        return optional.get();
    }
}
