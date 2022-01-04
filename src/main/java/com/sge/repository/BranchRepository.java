package com.sge.repository;

import com.sge.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description TODO
 * @createTime 2021/12/02
 */
@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {
    @Query("select count(b.id) from Branch b where b.id <> :id and b.platformId = :pId and b.branchName = :name")
    int countByName(@Param("id") int id, @Param("pId") int platformId, @Param("name") String name);
}
