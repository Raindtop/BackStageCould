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

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.raindrop.upms.dto.UserInfo;
import org.raindrop.upms.service.SysUserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author daoism
 * @date 2019年11月02日
 * <p>
 * 微信小程序
 */
@Slf4j
@Component("MINI")
@AllArgsConstructor
public class MiniAppLoginHandler implements LoginHandler{
	private final SysUserService sysUserService;

//	private final MiniHandleService miniHandleService;

	private final RedisTemplate redisTemplate;

	@Override
	public String identify(String loginStr) {
		return null;
	}

	@Override
	public UserInfo info(String identify) {
		return null;
	}
}
