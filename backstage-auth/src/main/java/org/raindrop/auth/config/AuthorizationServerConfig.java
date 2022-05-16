
package org.raindrop.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.raindrop.common.security.component.SecurityCommenceAuthExceptionEntryPoint;
import org.raindrop.common.security.component.SecurityWebResponseExceptionTranslator;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 认证服务器配置
 * @author raindrop
 * @date 2018/6/22
 */
@Configuration
@AllArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private final ClientDetailsService securityClientDetailsServiceImpl;

	private final AuthenticationManager authenticationManagerBean;

	private final UserDetailsService securityUserDetailsService;

	private final AuthorizationCodeServices authorizationCodeServices;

	private final TokenStore redisTokenStore;

//	private final TokenEnhancer tokenEnhancer;

	private final ObjectMapper objectMapper;

	@Override
	@SneakyThrows
	public void configure(ClientDetailsServiceConfigurer clients) {
		clients.withClientDetails(securityClientDetailsServiceImpl);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
		oauthServer.allowFormAuthenticationForClients()
				.authenticationEntryPoint(new SecurityCommenceAuthExceptionEntryPoint(objectMapper))
				.checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST).tokenStore(redisTokenStore)
				.userDetailsService(securityUserDetailsService)
				.authorizationCodeServices(authorizationCodeServices).authenticationManager(authenticationManagerBean)
				.reuseRefreshTokens(false).pathMapping("/oauth/confirm_access", "/token/confirm_access")
				.exceptionTranslator(new SecurityWebResponseExceptionTranslator());
	}

}
