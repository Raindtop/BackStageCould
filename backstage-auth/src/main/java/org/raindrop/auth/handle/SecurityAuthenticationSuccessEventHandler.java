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

package org.raindrop.auth.handle;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.raindrop.common.security.handle.AuthenticationSuccessHandler;
import org.raindrop.common.utils.WebUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author daoism
 * @date 2018/10/8
 */
@Slf4j
@Component
@AllArgsConstructor
public class SecurityAuthenticationSuccessEventHandler implements AuthenticationSuccessHandler {

	/**
	 * 处理登录成功方法
	 * <p>
	 * 获取到登录的authentication 对象
	 * @param authentication 登录对象
	 * @param request 请求
	 * @param response 返回
	 */
	@Async
	@Override
	public void handle(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
		String username = authentication.getName();
		log.info("用户：{} 登录成功", username);

		JSONObject jsonObject = new JSONObject();
		jsonObject.set("code", "2000");
		jsonObject.set("data", IdUtil.randomUUID());
		WebUtils.renderJson(response, jsonObject);
	}

}
