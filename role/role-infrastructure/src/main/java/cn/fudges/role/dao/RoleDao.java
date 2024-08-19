package cn.fudges.role.dao;

import cn.fudges.role.entity.Role;
import cn.fudges.role.dao.po.RolePoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangpingyuan
 * @since 2024-08-19
 */
@Service
public class RoleDao extends ServiceImpl<RolePoMapper, Role> {

}
