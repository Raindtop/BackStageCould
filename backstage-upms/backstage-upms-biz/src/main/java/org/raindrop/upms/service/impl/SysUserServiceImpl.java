package org.raindrop.upms.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.raindrop.upms.dto.UserInfo;
import org.raindrop.upms.entity.SysResource;
import org.raindrop.upms.entity.SysRole;
import org.raindrop.upms.entity.SysUser;
import org.raindrop.upms.handle.LoginHandler;
import org.raindrop.upms.mapper.SysRoleResourceMapper;
import org.raindrop.upms.mapper.SysUserMapper;
import org.raindrop.upms.mapper.SysUserRoleMapper;
import org.raindrop.upms.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    @Resource
    private SysRoleResourceMapper sysRoleResourceMapper;

    @Override
    public UserInfo getSysUserByName(String name) {
        // 查询用户信息
        SysUser sysUser = baseMapper.selectOne(Wrappers.lambdaQuery(SysUser.class)
                .eq(SysUser::getUsername, name)
                .last("limit 1"));

        if (sysUser == null) {
            return null;
        }

        UserInfo userInfo = getRoleResourceByUserId(sysUser.getUserId());
        userInfo.setSysUser(sysUser);

        return userInfo;
    }

    @Override
    public UserInfo getSysUserByOpenId(String openId) {
        // 查询用户信息
        SysUser sysUser = baseMapper.selectOne(Wrappers.lambdaQuery(SysUser.class)
                .eq(SysUser::getMiniOpenid, openId)
                .last("limit 1"));

        if (sysUser == null) {
            return null;
        }

        UserInfo userInfo = getRoleResourceByUserId(sysUser.getUserId());
        userInfo.setSysUser(sysUser);

        return userInfo;
    }

    @Override
    public UserInfo getUserInfoBySocial(String inStr) {
        String[] inStrs = inStr.split(StringPool.AT);
        String type = inStrs[0];
        String loginStr = inStrs[1];
        return loginHandlerMap.get(type).handle(loginStr);
    }


    @Override
    public UserInfo getRoleResourceByUserId(Integer userId) {
        // 获取权限信息
        List<SysRole> sysRoles = sysUserRoleMapper.getRoleByUserId(userId);
        List<Integer> roleIds = sysRoles.stream().map(SysRole::getRoleId).collect(Collectors.toList());
        List<SysResource> sysResources = new ArrayList<>();
        if (CollUtil.isNotEmpty(roleIds)){
            sysResources = sysRoleResourceMapper.getResourceByRoleIds(sysRoles.stream().map(SysRole::getRoleId).collect(Collectors.toList()));
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setRoles(ArrayUtil.toArray(sysRoles.stream().map(SysRole::getRoleId).collect(Collectors.toList()), Integer.class));
        userInfo.setPermissions(ArrayUtil.toArray(sysResources.stream().map(SysResource::getPermission).collect(Collectors.toSet()), String.class));

        return userInfo;
    }
}
