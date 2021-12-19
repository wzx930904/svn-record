package com.sge.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;

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
    @Column(name="id")//数据库字段名
    private Integer id;

    /*分支id*/
    @Column(name = "branch_id")
    private Integer branchId;

    @Column(name= "file_name")
    private String fileName;

    @Column(name = "svn_log")
    private String svnLog;

    @Column(name = "author")
    private String author;

    @Column(name = "date")
    private String date;

    /*变更说明*/
    @Column(name = "description")
    private String desc;


}
