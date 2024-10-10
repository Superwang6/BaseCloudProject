package ${customPackage.serviceImpl};

import ${customPackage.service}.${entity}Service;
import ${customPackage.po}.${entity}Po;
import ${customPackage.poDao}.${entity}PoMapper;
<#if generateService>
import ${customPackage.service}.${entity}Service;
</#if>
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}Po> implements ${entity}Service {

}

