package cn.fudges.role.service.impl;

import cn.fudges.role.service.AuthorityMenuService;
import cn.fudges.role.entity.po.AuthorityMenuPo;
import cn.fudges.role.dao.po.AuthorityMenuPoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单权限关联表 服务实现类
 * </p>
 *
 * @author wangpingyuan
 * @since 2025-03-17
 */
@Service
public class AuthorityMenuServiceImpl extends ServiceImpl<AuthorityMenuPoMapper, AuthorityMenuPo> implements AuthorityMenuService {

}

