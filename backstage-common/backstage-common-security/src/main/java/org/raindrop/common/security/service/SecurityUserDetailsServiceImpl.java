

package org.raindrop.common.security.service;

import cn.hutool.core.util.ArrayUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.raindrop.common.constants.CommonConstants;
import org.raindrop.common.constants.SecurityConstants;
import org.raindrop.common.security.bo.SecurityUser;
import org.raindrop.upms.api.RemoteUserService;
import org.raindrop.upms.dto.UserInfo;
import org.raindrop.upms.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户详细信息
 * @Author raindrop
 * @Date 2022/5/12
 **/
@Slf4j
@RequiredArgsConstructor
public class SecurityUserDetailsServiceImpl implements SecurityUserDetailsService {

	private final RemoteUserService remoteUserService;

	/**
	 * 用户密码登录
	 * @param username 用户名
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	@SneakyThrows
	public UserDetails loadUserByUsername(String username) {
		UserInfo result = remoteUserService.getSysUserByName(username, SecurityConstants.FROM_IN);
		UserDetails userDetails = getUserDetails(result);
		return userDetails;
	}

	/**
	 * 根据社交登录code 登录
	 * @param inStr TYPE@CODE
	 * @return UserDetails
	 * @throws UsernameNotFoundException
	 */
	@Override
	@SneakyThrows
	public UserDetails loadUserBySocial(String inStr) {
		return getUserDetails(remoteUserService.getSysUserBySocial(inStr, SecurityConstants.FROM_IN));
	}

	/**
	 * 构建userdetails
	 * @param info 用户信息
	 * @return
	 */
	private UserDetails getUserDetails(UserInfo info) {
		if (info == null) {
			throw new UsernameNotFoundException("用户不存在");
		}

		Set<String> dbAuthsSet = new HashSet<>();
		if (ArrayUtil.isNotEmpty(info.getRoles())) {
			// 获取角色
			Arrays.stream(info.getRoles()).forEach(roleId -> dbAuthsSet.add(SecurityConstants.ROLE + roleId));
			// 获取资源
			dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));

		}
		Collection<? extends GrantedAuthority> authorities = AuthorityUtils
				.createAuthorityList(dbAuthsSet.toArray(new String[0]));
		SysUser user = info.getSysUser();
		boolean enabled = user.getLocked().equals(CommonConstants.STATUS_NORMAL);
		// 构造security用户
		return new SecurityUser(user.getWxUnionid(), user.getNickname(), user.getMiniOpenid(), user.getUserId(), user.getPhone(), user.getAvatar(),
				user.getUsername(), SecurityConstants.BCRYPT + user.getPassword(), enabled, true, true,
				!CommonConstants.STATUS_LOCK.equals(user.getLocked()), authorities);
	}

}
