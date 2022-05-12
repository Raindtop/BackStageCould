package org.raindrop.common.security.mobile;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.raindrop.common.security.component.SecurityPreAuthenticationChecks;
import org.raindrop.common.security.service.SecurityUserDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * 手机登录校验逻辑 验证码登录、社交登录
 * @Author raindrop
 * @Date 2022/5/12
 **/
@Slf4j
public class MobileAuthenticationProvider implements AuthenticationProvider {


	private UserDetailsChecker detailsChecker = new SecurityPreAuthenticationChecks();

	@Getter
	@Setter
	private SecurityUserDetailsService userDetailsService;

	@Override
	@SneakyThrows
	public Authentication authenticate(Authentication authentication) {
		MobileAuthenticationToken mobileAuthenticationToken = (MobileAuthenticationToken) authentication;

		String principal = mobileAuthenticationToken.getPrincipal().toString();
		UserDetails userDetails = userDetailsService.loadUserBySocial(principal);
		if (userDetails == null) {
			log.debug("Authentication failed: no credentials provided");

			throw new BadCredentialsException("用户不存在");
		}

		// 检查账号状态
		detailsChecker.check(userDetails);

		MobileAuthenticationToken authenticationToken = new MobileAuthenticationToken(userDetails,
				userDetails.getAuthorities());
		authenticationToken.setDetails(mobileAuthenticationToken.getDetails());
		return authenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return MobileAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
