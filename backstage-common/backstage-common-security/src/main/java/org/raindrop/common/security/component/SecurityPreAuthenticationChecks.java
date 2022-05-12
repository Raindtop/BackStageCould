

package org.raindrop.common.security.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * 用户状态检查
 * @Author raindrop
 * @Date 2022/5/12
 **/
@Slf4j
public class SecurityPreAuthenticationChecks implements UserDetailsChecker {;

	@Override
	public void check(UserDetails user) {
		if (!user.isAccountNonLocked()) {
			log.debug("User account is locked");

			throw new LockedException("User account is locked");
		}

		if (!user.isEnabled()) {
			log.debug("User account is disabled");

			throw new DisabledException("User is disabled");
		}

		if (!user.isAccountNonExpired()) {
			log.debug("User account is expired");

			throw new AccountExpiredException("User account has expired");
		}
	}

}
