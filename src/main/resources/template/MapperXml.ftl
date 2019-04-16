<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- namespace必须指向Dao接口 -->
<mapper namespace="${scheme.packageName}.dao.${table.className}Dao">

    <resultMap id="${table.className}ResultMap" type="${scheme.packageName}.entity.${table.className}">
    <#list table.columns as column>
        <#if column.pk == 1>
            <id property="${column.javaField}" column="${column.name}"  />
        <#else>
            <result property="${column.javaField}" column="${column.name}" />
        </#if>
    </#list>
    </resultMap>

    <!-- 定义可重用的SQL代码段，可以包含在其他语句中 -->
    <sql id="base_column">
    <#list table.columns as column>${column.name} <#if column_has_next>,</#if></#list>
    </sql>

    <sql id="base_value_column">
    <#list table.columns as column>${r'#'}{${column.javaField}}<#if column_has_next>,</#if></#list>
    </sql>

    <!-- 定义可重用的WHERE SQL公共代码段，可以包含在其他语句中 -->
    <sql id="where_column">
    <#list table.columns as column>
        <if test="${column.javaField} != null ">
            AND ${column.name} ${column.queryType} <#if column.queryType == 'like'> CONCAT('%',${r'#'}{${column.javaField}},'%')<#else>${r'#'}{${column.javaField}<#if column.mybatisJdbcType??>,jdbcType=${column.mybatisJdbcType}</#if>}</#if>
        </if>
        <#if column.mybatisJdbcType == 'VARCHAR'>
            <if test="${column.javaField}like != null ">
                AND ${column.name} like CONCAT('%',${r'#'}{${column.javaField}like},'%')
            </if>
        </#if>
        <#if column.mybatisJdbcType == 'TIMESTAMP'>
            <!-- 大于等于${column.name} -->
            <if test="filter_${column.javaField}s != null">
                AND date_format(${column.name},'%Y%m%d')<![CDATA[>=]]>${r'#'}{filter_${column.javaField}s}
            </if>
            <!-- 小于等于${column.name} -->
            <if test="filter_${column.javaField}e!= null">
                AND date_format(${column.name},'%Y%m%d')<![CDATA[<=]]>${r'#'}{filter_${column.javaField}e}
            </if>
        </#if>

        <#if column.mybatisJdbcType == 'BIGINT' || column.mybatisJdbcType == 'INTEGER' || column.mybatisJdbcType == 'DECIMAL'  >
            <!-- 大于等于${column.name} -->
            <if test="filter_${column.javaField}s != null">
                AND ${column.name}<![CDATA[>=]]>${r'#'}{filter_${column.javaField}s}
            </if>
            <!-- 小于等于${column.name} -->
            <if test="filter_${column.javaField}e!= null">
                AND ${column.name}<![CDATA[<=]]>${r'#'}{filter_${column.javaField}e}
            </if>
        </#if>


    </#list>
    </sql>

    <!-- 设置语句 -->
    <sql id="set_column">
    <#list table.columns as column>
        <#if column.edit == 1>
            <if test="${column.javaField} != null ">
            ${column.name} = ${r'#'}{${column.javaField},jdbcType=${column.mybatisJdbcType}},
            </if>
        </#if>
    </#list>
    </sql>

    <!-- 根据ID查询 -->
    <select id="find" resultMap="${table.className}ResultMap">
        SELECT	<include refid="base_column" />
        FROM	${table.name}
        WHERE	ID=${r'#'}{id,jdbcType=BIGINT}
    </select>

    <!-- 查询所有 -->
    <select id="findAll" resultMap="${table.className}ResultMap">
        SELECT	<include refid="base_column" />
        FROM	${table.name}
    </select>

    <!-- 统计记录数 -->
    <select id="count" resultType="long">
        SELECT COUNT(*) FROM ${table.name}
        <where>
            <include refid="where_column" />
        </where>
    </select>

    <!-- 插入 -->
    <insert id="insert" parameterType="${scheme.packageName}.entity.${table.className}">
        <selectKey resultType="java.lang.Long" order="AFTER" keyProperty="id">
            SELECT LAST_INSERT_ID() AS ID
        </selectKey>
        INSERT ${table.name}
        (<include refid="base_column" /> )
        VALUES	(<include refid="base_value_column" />)
    </insert>

    <!-- 修改 -->
    <update id="update" parameterType="${scheme.packageName}.entity.${table.className}">
        UPDATE	${table.name}
        <set>
            <include refid="set_column" />
        </set>
        WHERE	ID = ${r'#'}{id,jdbcType=BIGINT}
    </update>

    <!-- 删除单条记录 -->
    <delete id="delete">
        DELETE	FROM ${table.name}
        WHERE	ID = ${r'#'}{id,jdbcType=BIGINT}
    </delete>


    <!-- 删除多条记录-->
    <delete id="deleteAll">
        DELETE	FROM ${table.name}
        WHERE	ID	IN
        <foreach item="${table.className}Id" index="index" collection="ids" open="(" separator="," close=")">
        ${r'#'}{${table.className}Id}
        </foreach>
    </delete>

    <!-- 根据参数查询列表-->
    <select id="findByParams" resultMap="${table.className}ResultMap">
        SELECT	<include refid="base_column" />
        FROM 	${table.name}
        <where> 1=1
            <include refid="where_column" />
        </where>
    </select>

    <!-- 分页查询-->
    <select id="findByPage" resultMap="${table.className}ResultMap">
        SELECT
        <include refid="base_column" />
        FROM ${table.name}
        <where> 1=1
            <include refid="where_column" />
        </where>
    </select>

    <!-- 分页查询-->
    <select id="findListByPage" resultMap="${table.className}ResultMap">
        SELECT
        <include refid="base_column" />
        FROM ${table.name}
        <where> 1=1
            <include refid="where_column" />
        </where>

        <if test="order != null and order != ''">
            order by  ${r'${order}'}
        </if>

        <if test="pageNumber != null  and pageSize !=null ">
            limit  ${r'#'}{pageNumber,jdbcType=INTEGER},${r'#'}{pageSize,jdbcType=INTEGER}
        </if>


    </select>


    <!-- 删除多条记录-->
    <delete id="deletes">
        DELETE	FROM ${table.name}
        <where>
            <include refid="where_column" />
        </where>
    </delete>



</mapper>