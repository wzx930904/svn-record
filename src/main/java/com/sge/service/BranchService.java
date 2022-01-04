package com.sge.service;

import com.sge.advice.BusinessException;
import com.sge.entity.Branch;
import com.sge.entity.Platform;
import com.sge.repository.BranchRepository;
import com.sge.utils.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by wzx on 2021/12/7.
 */
@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    PlatformService platformService;


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
        String createDate = DateFormatUtils.CNStringDateTrasfor(branch.getCreateDate());
        branch.setCreateDate(createDate);
        branchRepository.save(branch);
    }

    /**
     * 参数有效性检验
     * @param branch
     */
    private void vaildateParam(Branch branch) {
        if (StringUtils.isBlank(branch.getBranchName())) {
            throw new BusinessException("请输入分支名称");
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
        int platformId = branch.getPlatformId();
        int id = branch.getId();
        String name = branch.getBranchName();
        int count = branchRepository.countByName(id,platformId,name);
        if (count > 0) {
            throw new BusinessException("该系统下的分支名称不能重复");
        }
        String createDate = DateFormatUtils.CNStringDateTrasfor(branch.getCreateDate());
        branch.setCreateDate(createDate);
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

    /**
     * 分页查询
     * @param platformId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<Branch> findByPage(Integer platformId, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        Page<Branch> page = null;
        Branch query = new Branch();
        query.setPlatformId(platformId);
        Example<Branch> example = Example.of(query);
        page = branchRepository.findAll(example,pageable);
        if (page != null) {
            Platform platform = platformService.getById(platformId);
            String sysName = platform.getSystemName();
            page.getContent().forEach(branch -> {
                branch.setSystemName(sysName);
            });
        }
        return page;
    }
}
