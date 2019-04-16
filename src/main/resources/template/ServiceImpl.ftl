package ${scheme.packageName}.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${scheme.packageName}.dao.${table.className}Dao;
import ${scheme.packageName}.entity.${table.className};
import ${scheme.packageName}.service.${table.className}Service;
import java.util.List;
/**
* SERVICE - ${table.className}(${scheme.moduleName})
*
* @author ${scheme.author!'Loippi Team'}
* @version ${current_version}
*/
@Service
public class ${table.className}ServiceImpl extends GenericServiceImpl<${table.className}, Long> implements ${table.className}Service {

@Autowired
private ${table.className}Dao ${table.className?uncap_first}Dao;


@Autowired
public void setGenericDao() {
super.setGenericDao(${table.className?uncap_first}Dao);
}

public List<${table.className}> findListByPage(Object parameter){
return ${table.className?uncap_first}Dao.findListByPage(parameter);
}

public Long deletes(Object parameter){
return ${table.className?uncap_first}Dao.deletes( parameter);
}


}
