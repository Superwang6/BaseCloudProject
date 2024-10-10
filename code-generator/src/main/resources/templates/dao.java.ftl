package ${customPackage.dao};

import ${customPackage.po}.${entity}Po;
import ${customPackage.poDao}.${entity}PoMapper;
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
public class ${entity}Dao extends ${superServiceImplClass}<${entity}PoMapper, ${entity}Po> {

}
