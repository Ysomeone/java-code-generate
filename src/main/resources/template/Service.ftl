package ${scheme.packageName}.service;

import ${scheme.packageName}.entity.${table.className};
import java.util.List;
/**
* SERVICE - ${table.className}(${scheme.moduleName})
*
* @author ${scheme.author!'Loippi Team'}
* @version ${current_version}
*/
public interface ${table.className}Service  extends GenericService<${table.className}, Long> {
public	List<${table.className}> findListByPage(Object parameter);
public	 Long deletes(Object parameter);
}
