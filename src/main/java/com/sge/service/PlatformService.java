package com.sge.service;

import com.sge.advice.BusinessException;
import com.sge.entity.Branch;
import com.sge.entity.Platform;
import com.sge.repository.BranchRepository;
import com.sge.repository.PlatformRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    /**
     * 查询
     * @return
     */
    public List<Platform> findAll(){
        return platformRepository.findAll();
    }

    /**
     * 保存数据
     * @param platform
     */
    public void save(Platform platform) {
        validateParam(platform);
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
        if (StringUtils.isBlank(platform.getDesc())) {
            throw new BusinessException("请输入系统描述");
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
        platformRepository.save(platform);
    }

}
