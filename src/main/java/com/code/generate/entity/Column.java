package com.code.generate.entity;

import lombok.Data;

@Data
public class Column {
    /**
     * 主键
     */
    private Boolean isPrimaryKey;
    /**
     * 数据库字段名称
     **/
    private String name;
    /**
     * 数据库字段类型
     **/
    private String type;
    /**
     * 数据库字段首字母小写且去掉下划线字符串
     **/
    private String nameUp;
    /**
     * 数据库字段注释
     **/
    private String comment;
    /**
     * 原始字段名
     */
    private String originalName;
    /**
     *数据库表的字段类型
     */
    private String originalType;

}
