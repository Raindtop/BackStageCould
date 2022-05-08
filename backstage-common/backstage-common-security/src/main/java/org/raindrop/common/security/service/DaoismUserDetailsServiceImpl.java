///*
// *    Copyright (c) 2018-2025, daoism All rights reserved.
// *
// * Redistribution and use in source and binary forms, with or without
// * modification, are permitted provided that the following conditions are met:
// *
// * Redistributions of source code must retain the above copyright notice,
// * this list of conditions and the following disclaimer.
// * Redistributions in binary form must reproduce the above copyright
// * notice, this list of conditions and the following disclaimer in the
// * documentation and/or other materials provided with the distribution.
// * Neither the name of the pig4cloud.com developer nor the names of its
// * contributors may be used to endorse or promote products derived from
// * this software without specific prior written permission.
// * Author: daoism
// */
//
//package org.raindrop.common.security.service;
//
//import cn.hutool.core.util.ArrayUtil;
//import cn.hutool.core.util.StrUtil;
//import lombok.RequiredArgsConstructor;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.raindrop.common.constants.CommonConstants;
//import org.raindrop.common.constants.SecurityConstants;
//import org.raindrop.common.entity.R;
//import org.raindrop.upms.api.RemoteUserService;
//import org.raindrop.upms.dto.UserInfo;
//import org.raindrop.upms.entity.SysUser;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * 用户详细信息
// *
// * @author daoism
// */
//@Slf4j
//@RequiredArgsConstructor
//public class DaoismUserDetailsServiceImpl implements DaoismUserDetailsService {
//
//	private final RemoteUserService remoteUserService;
//
//	/**
//	 * 用户密码登录
//	 * @param username 用户名
//	 * @return
//	 * @throws UsernameNotFoundException
//	 */
//	@Override
//	@SneakyThrows
//	public UserDetails loadUserByUsername(String username) {
////		Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
////		if (cache != null && cache.get(username) != null) {
////			return (DaoismUser) cache.get(username).get();
////		}
//
//		R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
//		UserDetails userDetails = getUserDetails(result);
////		cache.put(username, userDetails);
////		String key =CommonConstants.TENANT_ID_1+":"+CacheConstants.USER_DETAILS+"::"+username;
////		redisTemplate.expire(key, Duration.ofHours(24));
//		return userDetails;
//	}
//
//	/**
//	 * 根据社交登录code 登录
//	 * @param inStr TYPE@CODE
//	 * @return UserDetails
//	 * @throws UsernameNotFoundException
//	 */
//	@Override
//	@SneakyThrows
//	public UserDetails loadUserBySocial(String inStr) {
//		return getUserDetails(remoteUserService.social(inStr, SecurityConstants.FROM_IN));
//	}
//
//	/**
//	 * 构建userdetails
//	 * @param result 用户信息
//	 * @return
//	 */
//	private UserDetails getUserDetails(R<UserInfo> result) {
//		if (result == null || result.getData() == null) {
//			throw new UsernameNotFoundException("用户不存在");
//		}
//
//		UserInfo info = result.getData();
//		Set<String> dbAuthsSet = new HashSet<>();
//		if (ArrayUtil.isNotEmpty(info.getRoles())) {
//			// 获取角色
//			Arrays.stream(info.getRoles()).forEach(roleId -> dbAuthsSet.add(SecurityConstants.ROLE + roleId));
//			// 获取资源
//			dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));
//
//		}
//		Collection<? extends GrantedAuthority> authorities = AuthorityUtils
//				.createAuthorityList(dbAuthsSet.toArray(new String[0]));
//		SysUser user = info.getSysUser();
//		boolean enabled = user.getLocked().equals(CommonConstants.STATUS_NORMAL);
//		// 构造security用户
//		return new DaoismUser(user.getWxUnionid(), user.getMiniOpenid(), user.getUserId().intValue(), user.getPhone(), user.getAvatar(),
//				user.getUsername(), SecurityConstants.BCRYPT + user.getPassword(), enabled, true, true,
//				!CommonConstants.STATUS_LOCK.equals(user.getLocked()), authorities);
//	}
//
//}
