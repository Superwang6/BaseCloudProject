package cn.fudges.role.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王平远
 * @since 2024/9/13
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @RequestMapping("/test")
    public String test(){
        return "role test";
    }
}
