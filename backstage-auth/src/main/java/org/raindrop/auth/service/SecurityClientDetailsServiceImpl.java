package org.raindrop.auth.service;

import org.raindrop.common.constants.CacheConstants;
import org.raindrop.common.constants.SecurityConstants;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 *
 * @Author raindrop
 * @Date 2022/5/12
 **/
@Service
public class SecurityClientDetailsServiceImpl extends JdbcClientDetailsService {

    public SecurityClientDetailsServiceImpl(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 重写原生方法支持redis缓存
     *
     * @param clientId
     * @return ClientDetails
     * @throws InvalidClientException
     */
    @Override
    @Cacheable(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
    public ClientDetails loadClientByClientId(String clientId) {
        super.setSelectClientDetailsSql(SecurityConstants.DEFAULT_SELECT_STATEMENT);
        return super.loadClientByClientId(clientId);
    }

}
