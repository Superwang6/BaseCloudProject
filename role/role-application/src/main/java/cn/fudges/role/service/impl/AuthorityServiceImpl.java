package cn.fudges.role.service.impl;

import cn.fudges.role.service.AuthorityService;
import cn.fudges.role.entity.po.AuthorityPo;
import cn.fudges.role.dao.po.AuthorityPoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author wangpingyuan
 * @since 2025-03-17
 */
@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityPoMapper, AuthorityPo> implements AuthorityService {

}

