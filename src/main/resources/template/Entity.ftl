package ${scheme.packageName}.entity;

import ${GenericEntity};
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Entity - ${scheme.moduleName}
*
* @author ${scheme.author!'Loippi Team'}
* @version ${current_version}
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "${table.name}")
public class ${table.className} implements GenericEntity {

private static final long serialVersionUID = 5081846432919091193L;

<#list table.columns as column>
/** ${column.comments} */
    <#if column.pk == 1>
    @Column(id = true, name = "${column.name}", updatable = false)
    <#else>
    @Column(name = "${column.name}"<#if column.edit == 0>, updatable = false</#if> <#if column.insert == 0>, insertable = false</#if>)
    </#if>
private ${column.javaType} ${column.javaField};

</#list>
}
