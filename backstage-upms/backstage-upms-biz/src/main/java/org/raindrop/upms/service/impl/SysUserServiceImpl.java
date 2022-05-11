package org.raindrop.upms.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.raindrop.upms.dto.UserInfo;
import org.raindrop.upms.entity.SysUser;
import org.raindrop.upms.handle.LoginHandler;
import org.raindrop.upms.mapper.SysUserMapper;
import org.raindrop.upms.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 用户信息
 *
 * @author raindrop
 * @date ${base.datetime}
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private Map<String, LoginHandler> loginHandlerMap;

    @Override
    public UserInfo getUserInfo(String inStr) {
        String[] inStrs = inStr.split(StringPool.AT);
        String type = inStrs[0];
        String loginStr = inStrs[1];
        return loginHandlerMap.get(type).handle(loginStr);
    }
}
