

package org.raindrop.upms.handle;

import org.raindrop.upms.dto.UserInfo;

/**
 * 登陆处理器
 *
 * @Author raindrop
 * @Date 2022/5/10
 **/
public interface LoginHandler {

    /***
     * 数据合法性校验
     * @param loginStr 通过用户传入获取唯一标识
     * @return
     */
    default Boolean check(String loginStr){
        return true;
    };

    /**
     * 通过用户传入获取唯一标识 openId
     *
     * @param loginStr
     * @return
     */
    String identify(String loginStr);

    /**
     * 通过openId 获取用户信息
     *
     * @param identify
     * @return
     */
    UserInfo info(String identify);

    /**
     * 处理方法
     *
     * @param loginStr 登录参数
     * @return
     */
    default UserInfo handle(String loginStr){
        if (!check(loginStr)) {
            return null;
        }

        String identify = identify(loginStr);
        return info(identify);
    };

}
