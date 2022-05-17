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

package org.raindrop.common.security.component;

import cn.hutool.core.map.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.raindrop.common.constants.SecurityConstants;
import org.raindrop.common.security.bo.SecurityUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author daoism
 * @date 2019-03-07
 * <p>
 * 根据checktoken 的结果转化用户信息
 */
@Slf4j
public class SecurityUserAuthenticationConverter implements UserAuthenticationConverter {

    private static final String N_A = "N/A";

    /**
     * Extract information about the user to be used in an access token (i.e. for resource
     * servers).
     *
     * @param authentication an authentication representing a user
     * @return a map of key values representing the unique information about the user
     */
    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put(USERNAME, authentication.getName());
        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        return response;
    }

    /**
     * Inverse of {@link #convertUserAuthentication(Authentication)}. Extracts an
     * Authentication from a map.
     *
     * @param responseMap a map of user information
     * @return an Authentication representing the user or null if there is none
     */
    @Override
    public Authentication extractAuthentication(Map<String, ?> responseMap) {
        if (responseMap.containsKey(USERNAME)) {
            Collection<? extends GrantedAuthority> authorities = getAuthorities(responseMap);
            Map<String, ?> map = MapUtil.get(responseMap, SecurityConstants.DETAILS_USER, Map.class);
            String username = MapUtil.getStr(map, SecurityConstants.DETAILS_USERNAME);
            Integer id = MapUtil.getInt(map, SecurityConstants.DETAILS_USER_ID);
            String phone = MapUtil.getStr(map, SecurityConstants.DETAILS_PHONE);
            String avatar = MapUtil.getStr(map, SecurityConstants.DETAILS_AVATAR);
            String miniOpenId = MapUtil.getStr(map, SecurityConstants.MINI_OPEN_ID);
            String nickName = MapUtil.getStr(map, SecurityConstants.MINI_NICK_NAME);
            String unionId = MapUtil.getStr(map, SecurityConstants.WX_UNIONID);
            SecurityUser user = new SecurityUser(unionId, nickName, miniOpenId, id,
                    phone, avatar, username, N_A, true, true, true, true,
                    authorities);
            return new UsernamePasswordAuthenticationToken(user, N_A, authorities);
        }
        return null;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
        Object authorities = map.get(AUTHORITIES);
        if (authorities instanceof String) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
        }
        if (authorities instanceof Collection) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(
                    StringUtils.collectionToCommaDelimitedString((Collection<?>) authorities));
        }
        return AuthorityUtils.NO_AUTHORITIES;
    }
}
