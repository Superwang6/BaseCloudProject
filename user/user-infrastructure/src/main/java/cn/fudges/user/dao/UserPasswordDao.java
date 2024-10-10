package cn.fudges.user.dao;

import cn.fudges.user.dao.po.UserPasswordPoMapper;
import cn.fudges.user.entity.po.UserPasswordPo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户密码信息 服务实现类
 * </p>
 *
 * @author wangpingyuan
 * @since 2024-10-09
 */
@Service
public class UserPasswordDao extends ServiceImpl<UserPasswordPoMapper, UserPasswordPo> {

}
