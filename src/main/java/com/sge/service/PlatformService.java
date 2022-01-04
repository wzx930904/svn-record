package com.sge.service;

import com.sge.advice.BusinessException;
import com.sge.entity.Branch;
import com.sge.entity.Platform;
import com.sge.repository.BranchRepository;
import com.sge.repository.PlatformRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author wzm
 * @Description TODO
 * @createTime 2021/12/02
 */
@Service
public class PlatformService {

    @Autowired
    private PlatformRepository platformRepository;
    @Autowired
    private BranchRepository branchRepository;

    /**
     * 查询
     * @return
     * @param sysCode
     * @param pageSize
     * @param pageNum
     */
    public Page<Platform> findByPage(String sysCode, int pageSize, int pageNum){
        Sort sort =  Sort.by(Sort.Direction.DESC,"createTime");
        Pageable pageable = PageRequest.of(pageNum-1,pageSize,sort);
        Page<Platform> page = null;
        if (StringUtils.isBlank(sysCode)) {
            page = platformRepository.findAll(pageable);
        } else {
            Platform query = new Platform();
            query.setSystemCode(sysCode);
            Example<Platform> example = Example.of(query);
            page = platformRepository.findAll(example,pageable);
        }

        return page;
    }

    /**
     * 保存数据
     * @param platform
     */
    public void save(Platform platform) {
        validateParam(platform);
        Platform codeValid = new Platform();
        codeValid.setSystemCode(platform.getSystemName());
        Example<Platform> exampleCode = Example.of(codeValid);
        long countCode = platformRepository.count(exampleCode);
        if (countCode > 0) {
            throw new BusinessException("系统编码不能重复");
        }
        Platform nameValid = new Platform();
        nameValid.setSystemName(platform.getSystemName());
        Example<Platform> exampleName = Example.of(nameValid);
        long countName = platformRepository.count(exampleName);
        if (countName > 0) {
            throw new BusinessException("系统名称不能重复");
        }
        platform.setCreateTime(new Date(System.currentTimeMillis()));
        platform.setUpdateTime(new Date(System.currentTimeMillis()));
        platformRepository.save(platform);
    }

    /**
     * 参数有效性校验
     * @param platform
     */
    private void validateParam(Platform platform) {
        if (StringUtils.isBlank(platform.getSystemName())) {
            throw new BusinessException("请输入系统名称");
        }
        if (StringUtils.isBlank(platform.getSystemCode())) {
            throw new BusinessException("请输入系统编码");
        }
    }

    /**
     * 删除
     * @param id 主键id
     */
    public void delete(Integer id) {
        boolean flag = platformRepository.existsById(id);
        if (!flag) {
            throw new BusinessException("该记录不存在");
        }
        Branch branch = new Branch();
        branch.setPlatformId(id);
        Example<Branch> example= Example.of(branch);
        long count = branchRepository.count(example);
        if (count > 0) {
            throw new BusinessException("该系统存在分支不能删除");
        }
        platformRepository.deleteById(id);
    }

    /**
     * 更新系统信息
     * @param platform
     */
    public void update(Platform platform) {
        boolean flag = platformRepository.existsById(platform.getId());
        if (!flag) {
            throw new BusinessException("该记录不存在");
        }
        validateParam(platform);
        int id = platform.getId();
        String name = platform.getSystemName();
        String code = platform.getSystemCode();
        int nameCount = platformRepository.countByNameOrCode(id,null,code);
        if (nameCount > 0) {
            throw new BusinessException("系统编码不能重复");
        }
        int codeCount = platformRepository.countByNameOrCode(id,name,null);
        if (codeCount > 0) {
            throw new BusinessException("系统名称不能重复");
        }
        platformRepository.save(platform);
    }

    public List<Platform> findAll() {
        return platformRepository.findAll();
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public Platform getById(Integer id) {
        Optional<Platform> optional = platformRepository.findById(id);
        return optional.get();
    }
}
