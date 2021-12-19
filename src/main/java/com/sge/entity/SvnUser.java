package com.sge.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 用户
 * Created by wzx on 2021/12/16.
 */
@Getter
@Setter
@Table(name = "T_SVN_USER")
@DynamicInsert
@DynamicUpdate
public class SvnUser {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键生成策略
    @Column(name="F_ID")//数据库字段名
    private Integer id;

    @Column(name = "F_UER_ID")
    private String userId;

    @Column(name = "F_USER_NAME")
    private String userName;

    @Column(name = "F_PASSWORD")
    private String password;

    @Column(name = "F_STATUS")
    private short status;
}
