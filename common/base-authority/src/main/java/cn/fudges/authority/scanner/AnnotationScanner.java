package cn.fudges.authority.scanner;

import cn.fudges.authority.annotation.Login;
import cn.fudges.authority.annotation.PreAuthority;
import cn.fudges.authority.controller.TestController;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import java.util.Map;
import java.util.Set;

/**
 * @author 王平远
 * @since 2025/4/10
 */
@Component
@RequiredArgsConstructor
public class AnnotationScanner {

    private final ApplicationContext applicationContext;

    private final RequestMappingInfoHandlerMapping handlerMapping;

    @PostConstruct
    public void scanAnnotation(){
        String[] names = applicationContext.getBeanDefinitionNames();
        for(String name : names){
            Object bean = applicationContext.getBean(name);
            Class<?> clazz = bean.getClass();

            // 判断类上是否有注解
            if(clazz.isAnnotationPresent(Login.class) || clazz.isAnnotationPresent(PreAuthority.class)) {

            }

            // 判断方法上是否有注解
        }
    }

    public static void main(String[] args) {
        Class<?> targetController = TestController.class; // 替换为你的 Controller 类

//        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
//        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
//            HandlerMethod method = entry.getValue();
//            if (method.getBeanType().equals(targetController)) {
//                RequestMappingInfo info = entry.getKey();
//                Set<String> patterns = info.getPatternsCondition().getPatterns();
//                Set<RequestMethod> methods = info.getMethodsCondition().getMethods();
//
//                System.out.println("方法：" + method.getMethod().getName());
//                System.out.println("URL：" + patterns);
//                System.out.println("请求方式：" + methods);
//            }
//        }
    }

}
