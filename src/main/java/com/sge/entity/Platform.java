package com.sge.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Date;

/**
 * @Description 平台
 */
@Entity
@Table(name="t_platform")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
public class Platform {

    /*主键id*/
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键生成策略
    @Column(name="F_ID")//数据库字段名
    private Integer id;

    /*平台名称*/
    @Column(name= "F_SYSTEM_NAME")
    private String systemName;

    /*平台描述*/
    @Column(name = "F_SYSTEM_CODE")
    private String systemCode;

    @Column(name = "F_CREATE_TIME")
    private Date createTime;

    @Column(name = "F_UPDATE_TIME")
    private Date updateTime;

}
