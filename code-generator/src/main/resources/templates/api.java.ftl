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
@FeignClient(name = "${moduleName}")
public interface ${entity}Api {

}

