package com.sge.repository;

import com.sge.entity.SvnRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description TODO
 * @createTime 2021/12/02
 */
@Repository
public interface SvnRecordRepository extends JpaRepository<SvnRecord, Integer> {
}
