package org.raindrop.common.security.serialize;

import lombok.RequiredArgsConstructor;
import org.raindrop.common.constants.SecurityConstants;
import org.raindrop.common.security.component.SecurityRedisTokenStore;
import org.raindrop.common.security.serialize.JacksonRedisTokenStoreSerializationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author daoism
 * @date 2020/9/29
 * <p>
 * redis token store 自动配置
 */
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
public class SecurityTokenStoreAutoConfiguration {
	private final RedisConnectionFactory connectionFactory;

	@Bean
	public TokenStore tokenStore() {
		SecurityRedisTokenStore tokenStore = new SecurityRedisTokenStore(connectionFactory);
		if (SecurityConstants.JSON_FORMAT) {
			tokenStore.setSerializationStrategy(new JacksonRedisTokenStoreSerializationStrategy());
		}
		tokenStore.setPrefix(SecurityConstants.SYSTEM_PREFIX + SecurityConstants.OAUTH_PREFIX);

		return tokenStore;
	}

}
