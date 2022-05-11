/*
 *    Copyright (c) 2018-2025, daoism All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: daoism
 */

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
     * 通过用户传入获取唯一标识
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
