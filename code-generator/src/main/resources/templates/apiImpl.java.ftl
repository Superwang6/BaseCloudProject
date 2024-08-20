package ${customPackage.apiImpl};

import ${customPackage.api}.${entity}Api;
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ${author}
 * @since ${date}
 */
@RestController
public class ${entity}ApiImpl implements ${entity}Api {

}

