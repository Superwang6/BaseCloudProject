package ${customPackage.api};

<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import org.springframework.cloud.openfeign.FeignClient;

/**
 *
 * @author ${author}
 * @since ${date}
 */
@FeignClient(name = "${moduleName}-service", contextId = "${entity}Api")
public interface ${entity}Api {

}

