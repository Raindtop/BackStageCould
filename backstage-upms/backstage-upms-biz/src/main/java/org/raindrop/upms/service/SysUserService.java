package org.raindrop.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.raindrop.upms.dto.UserInfo;
import org.raindrop.upms.entity.SysUser;

/**
 * 用户信息
 *
 * @author raindrop
 * @date ${base.datetime}
 */
public interface SysUserService extends IService<SysUser> {
    /**
     * 根据用户名查询用户信息
     *
     * @param name
     * @return
     */
    UserInfo getSysUserByName(String name);

    /**
     * 根据openId查询用户信息
     *
     * @param name
     * @return
     */
    UserInfo getSysUserByOpenId(String openId);

    /**
     * 根据入参查询用户信息
     *
     * @param inStr TYPE@code
     * @return
     */
    UserInfo getUserInfoBySocial(String inStr);

    /**
     * 查询用户的角色以及权限信息
     *
     * @param userId
     * @return
     */
    UserInfo getRoleResourceByUserId(Integer userId);
}
