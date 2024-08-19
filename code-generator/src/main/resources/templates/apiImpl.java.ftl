package ${customPackage.apiImpl};

import ${customPackage.api}.${entity}Api;
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 *
 * @author ${author}
 * @since ${date}
 */
public class ${entity}ApiImpl implements ${entity}Api {

}

