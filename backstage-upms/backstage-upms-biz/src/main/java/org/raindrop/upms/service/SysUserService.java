package org.raindrop.upms.service;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.service.IService;
import org.raindrop.upms.dto.UserInfo;
import org.raindrop.upms.entity.SysUser;

import java.util.Map;

/**
 * 用户信息
 *
 * @author raindrop
 * @date ${base.datetime}
 */
public interface SysUserService extends IService<SysUser> {
    /**
     * 根据入参查询用户信息
     * @param inStr TYPE@code
     * @return
     */
    UserInfo getUserInfo(String inStr);
}
