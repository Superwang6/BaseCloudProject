package ${customPackage.controller};

<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ${author}
 * @since ${date}
 */
@RestController
@RequestMapping("/${moduleName}/${entityKC}")
public class ${entity}Controller {

}

