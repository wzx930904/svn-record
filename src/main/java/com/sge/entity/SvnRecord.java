package com.sge.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.sql.Date;

/**
 * @Description svn记录
 */
@Entity
@Table(name="t_svn_record")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
public class SvnRecord {

    /*主键id*/
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键生成策略 数据库自动生成（自动增长型）
    @Column(name="F_ID")//数据库字段名
    private Integer id;

    /*分支id*/
    @Column(name = "F_BRANCH_ID")
    private Integer branchId;

    @Column(name= "F_FILE_NAME")
    private String fileName;

    @Column(name = "F_SVN_LOG")
    private String svnLog;

    @Column(name = "F_AUTHOR")
    private String author;

    @Column(name = "F_DATE")
    private String date;

    /*变更说明*/
    @Column(name = "F_DESCRIPTION")
    private String desc;

    @Column(name = "f_create_time")
    private Date createTime;

    @Column(name = "f_update_time")
    private Date updateTime;
}
