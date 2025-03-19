package cn.fudges.user.service;

import cn.fudges.user.entity.UserBase;
import cn.fudges.user.entity.po.UserBasePo;
import cn.fudges.user.request.UserBaseRequest;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户基础信息 服务类
 * </p>
 *
 * @author wangpingyuan
 * @since 2024-09-27
 */
public interface UserBaseService extends IService<UserBasePo> {

    UserBase queryUserByUsername(UserBaseRequest request);
}
