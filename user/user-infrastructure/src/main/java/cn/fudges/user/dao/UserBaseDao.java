package cn.fudges.user.dao;

import cn.fudges.user.entity.UserBase;
import cn.fudges.user.dao.po.UserBasePoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基础信息 服务实现类
 * </p>
 *
 * @author wangpingyuan
 * @since 2024-09-19
 */
@Service
public class UserBaseDao extends ServiceImpl<UserBasePoMapper, UserBase> {

}
