package cn.fudges.role.service;

import cn.fudges.role.entity.Role;
import cn.fudges.role.service.impl.RoleServiceImpl;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王平远
 * @since 2024/8/19
 */
@SpringBootTest
public class RoleServiceTest {
    @Autowired
    private RoleService roleService;

    @Test
    public void test() {
        List<Role> roleList = new ArrayList<>();
        Role role1 = new Role();
        role1.setId(3);
        role1.setName("3333");
        role1.setRoleDesc("4444");
        roleList.add(role1);
        roleService.saveBatch(roleList);
    }
}
