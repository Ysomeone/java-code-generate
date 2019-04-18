<?xml version="1.0" encoding="UTF-8" ?>
<!DOCoriginalType mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.mapper.${modelName}Mapper">
    <resultMap id="BaseResultMap" originalType="${packageName}.entity.${modelName}">
        <#list columnList as model>
        <#if model.isPrimaryKey?string("true","false")! ="true">
        	<id column="${model.originalName!}" property="${model.name!}"/>
        <#else>
        	<result column="${model.originalName!}" property="${model.name!}"/>
        </#if>
        </#list>
    </resultMap>
    <sql id="Base_Column_List">
    <#if columnList?exists>
        <#list columnList as model>
            <#if model_has_next!=true>
                ${model.originalName!} AS ${model.name!}
            <#else>
                ${model.originalName!} AS ${model.name!},
            </#if>
        </#list>
    </#if>
    </sql>
    <sql id="where_column">
			<#list columnList as column>
                <if test="${column.nameUp} != null ">
                    AND ${column.originalName} = ${r'#'}{${column.nameUp}}
                </if>
	    <#if column.originalType == 'VARCHAR' ||  column.originalType == 'CHAR'>
	     <if test="${column.nameUp}like != null ">
             AND ${column.originalName} like CONCAT('%',${r'#'}{${column.nameUp}like},'%')
         </if>
        </#if>
	   <#if column.originalType == 'TIMESTAMP'>
	    	<!-- 大于等于${column.name} -->
		 <if test="filter_${column.nameUp}s != null">
             AND date_format(${column.originalName},'%Y-%m-%d')<![CDATA[>=]]>${r'#'}{filter_${column.nameUp}s}
         </if>
		<!-- 小于等于${column.name} -->
	     <if test="filter_${column.nameUp}e!= null">
             AND date_format(${column.originalName},'%Y-%m-%d')<![CDATA[<=]]>${r'#'}{filter_${column.nameUp}e}
         </if>
       </#if>
	   <#if column.originalType == 'BIGINT' || column.originalType == 'INTEGER' || column.originalType == 'DECIMAL'  >
	    	<!-- 大于等于${column.name} -->
		<if test="${column.nameUp}s != null">
            AND ${column.originalName}<![CDATA[>=]]>${r'#'}{${column.nameUp}s}
        </if>
		<!-- 小于等于${column.name} -->
	    <if test="${column.nameUp}e!= null">
            AND ${column.originalName}<![CDATA[<=]]>${r'#'}{${column.nameUp}e}
        </if>
       </#if>
            </#list>
    </sql>


</mapper>