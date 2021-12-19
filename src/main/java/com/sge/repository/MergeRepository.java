package com.sge.repository;

import com.sge.entity.MergeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wzx on 2021/12/10.
 */
@Repository
public interface MergeRepository extends JpaRepository<MergeInfo,Integer>{
}
