package com.code.generate.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 描述： 类解释 实体类
 * @author
 * @date
 */

@Table(name="Database")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Database{

    /**
     *主键id
     */
    @Id
    @Column( name="id")
    private Integer id;
    /**
     *数据库类型
     */
    @Column( name="database_type")
    private String databaseType;
    /**
     *ip地址
     */
    @Column( name="ip")
    private String ip;
    /**
     *端口号
     */
    @Column( name="port")
    private String port;
    /**
     *数据库名称
     */
    @Column( name="database_name")
    private String databaseName;
    /**
     *用户名
     */
    @Column( name="username")
    private String username;
    /**
     *密码
     */
    @Column( name="password")
    private String password;
    /**
     *驱动名称
     */
    @Column( name="driver")
    private String driver;
    /**
     *状态（1、启动 2、禁用）
     */
    @Column( name="status")
    private Boolean status;
    /**
     *创建时间
     */
    @Column( name="create_time")
    private Date createTime;
    /**
     *更新时间
     */
    @Column( name="update_time")
    private Date updateTime;
}
