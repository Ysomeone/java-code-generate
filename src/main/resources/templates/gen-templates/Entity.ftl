

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
* 描述： ${tableComment} 实体类
* @author
* @date
*/

@Table(name="${tableName}")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ${name}  {

<#if columnList?exists>
    <#list columnList as column>
    /**
     *${column.comment}
     */
    <#if column.isPrimaryKey==true>
    @Id
    </#if>
    @Column( name="${column.originalName}")
    private ${column.type!} ${column.name!};
    </#list>
</#if>
}
