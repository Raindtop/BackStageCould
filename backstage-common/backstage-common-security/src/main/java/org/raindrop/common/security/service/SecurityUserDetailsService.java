
package org.raindrop.common.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Author raindrop
 * @Date 2022/5/12
 **/
public interface SecurityUserDetailsService extends UserDetailsService {

	/**
	 * 根据社交登录code 登录
	 * @param code TYPE@CODE
	 * @return UserDetails
	 * @throws UsernameNotFoundException
	 */
	UserDetails loadUserBySocial(String code) throws UsernameNotFoundException;

}
