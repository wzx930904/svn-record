package com.sge.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by wzx on 2021/12/10.
 */
@Entity
@Table(name="t_merge_info")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
public class MergeInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键生成策略
    @Column(name="ID")//数据库字段名
    private Integer id;
    @Column(name="F_BRANCH_ID")
    private Integer branchId;
    @Column(name="F_MERGE_BRANCH_ID")
    private Integer mergeBranchId;
    private String branchName;
    private String mergeBranchName;
    @Column(name="F_MESSAGE")
    private String message;
    @Column(name="F_MERGE_OPERATOR")
    private String mergeOperator;
    @Column(name = "F_MERGE_DATE")
    private String mergeDate;
}
