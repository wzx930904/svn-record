package com.sge.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

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
    @Column(name="id")//数据库字段名
    private Integer id;

    @Column(name= "name")
    private String name;

    /*分支创建日期*/
    @Column(name = "create_data")
    private String createDate;

    @Column(name = "status")
    private String status;

    /*平台id*/
    @Column(name = "platform_id")
    private Integer platformId;

    @Column(name = "description")
    private String desc;

}
