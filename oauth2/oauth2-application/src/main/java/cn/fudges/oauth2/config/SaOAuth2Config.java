package cn.fudges.oauth2.config;

import cn.fudges.common.result.ResultResponse;
import cn.fudges.common.utils.AssertUtils;
import cn.fudges.user.api.UserBaseApi;
import cn.fudges.user.request.UserBaseRequest;
import cn.fudges.user.response.UserBaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author 王平远
 * @since 2024/9/18
 */
//@Configuration
public class SaOAuth2Config {

//    @Autowired
//    UserBaseApi userBaseApi;
//
//    @Autowired
//    public void configOAuth2Server(SaOAuth2ServerConfig oauth2Server) {
//
//        // 配置：未登录时返回的View
//        oauth2Server.notLoginView = () -> {
//            String msg = "当前会话在OAuth-Server端尚未登录，请先访问"
//                    + "<a href='/oauth2/doLogin?name=admin&pwd=123456' target='_blank'> doLogin登录 </a>"
//                    + "进行登录之后，刷新页面开始授权";
//            return msg;
//        };
//
//        // 配置：登录处理函数
//        oauth2Server.doLoginHandle = (name, pwd) -> {
//            UserBaseRequest request = UserBaseRequest.builder().userName(name).password(pwd).build();
//            ResultResponse<Boolean> loginStatusResponse = userBaseApi.checkLoginUserNameAndPassword(request);
//            AssertUtils.isSuccess(loginStatusResponse);
//            StpUtil.login(1);
//            if(loginStatusResponse.getData()) {
//                return ResultResponse.success("登录成功");
//            }
//            return ResultResponse.success("账号或密码错误！");
//        };
//
//        // 配置：确认授权时返回的 view
//        oauth2Server.confirmView = (clientId, scopes) -> {
//            String scopeStr = SaFoxUtil.convertListToString(scopes);
//            String yesCode =
//                    "fetch('/oauth2/doConfirm?client_id=" + clientId + "&scope=" + scopeStr + "', {method: 'POST'})" +
//                            ".then(res => res.json())" +
//                            ".then(res => location.reload())";
//            String res = "<p>应用 " + clientId + " 请求授权：" + scopeStr + "，是否同意？</p>"
//                    + "<p>" +
//                    "        <button onclick=\"" + yesCode + "\">同意</button>" +
//                    "        <button onclick='history.back()'>拒绝</button>" +
//                    "</p>";
//            return res;
//        };
//    }
}
