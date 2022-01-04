package com.sge.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Date;

/**
 * @Description 分支
 */
@Entity
@Table(name="t_branch")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
public class Branch {

    /*主键id*/
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键生成策略
    @Column(name="F_ID")//数据库字段名
    private Integer id;

    /*平台id*/
    @Column(name = "F_PLATFORM_ID")
    private Integer platformId;

    @Transient
    private String systemName;

    @Column(name= "F_BRANCH_NAME")
    private String branchName;

    /*分支创建日期*/
    @Column(name = "F_CREATE_DATA")
    private String createDate;

    @Column(name = "F_DESCRIPTION")
    private String desc;

    @Column(name = "F_CREATE_TIME")
    private Date createTime;

    @Column(name = "F_UPDATE_TIME")
    private Date updateTime;
}
