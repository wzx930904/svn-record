package com.sge.repository;

import com.sge.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description TODO
 * @createTime 2021/12/02
 */
@Repository
public interface PlatformRepository extends JpaRepository<Platform, Integer> {
    @Query("select count(f_id) from Platform p where  p.id <> :id and"+
            "(:name is null or p.systemName= :name) and" +
              "(:code is null or p.systemCode = :code)")
    int countByNameOrCode(@Param("id") int id, @Param("name") String name, @Param("code") String code);
}
