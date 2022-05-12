

package org.raindrop.upms.handle;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.raindrop.common.security.bo.SecurityUser;
import org.raindrop.common.security.utils.SecurityUtils;
import org.raindrop.upms.constants.WxConstants;
import org.raindrop.upms.dto.UserInfo;
import org.raindrop.upms.dto.wx.WxLoginResponseDto;
import org.raindrop.upms.service.SysUserService;
import org.raindrop.upms.utils.WxUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @Author raindrop
 * @Date 2022/5/12
 **/
@Slf4j
@Component("MINI")
@AllArgsConstructor
public class MiniAppLoginHandler implements LoginHandler {
    private final SysUserService sysUserService;

    private final WxUtils wxUtils;

    private final RedisTemplate redisTemplate;

    @Override
    public String identify(String code) {
        WxLoginResponseDto wxLogin = wxUtils.getOpenIdByWxCode(code);
        if (wxLogin == null) {
            return null;
        }

        String sessionKey = wxLogin.getSessionKey();
        String openid = wxLogin.getOpenid();
        String cacheKey = WxConstants.WX_CACHE_SESSION_KEY + openid;
        //缓存sessionKey
        redisTemplate.opsForValue().set(cacheKey, sessionKey, Duration.ofDays(6));
        return openid;
    }

    private String getSessionKey() {
        SecurityUser securityUser = SecurityUtils.getUser();
        if (securityUser == null) {
            return null;
        }

        String openid = securityUser.getMiniOpenId();
        String cacheKey = WxConstants.WX_CACHE_SESSION_KEY + openid;
        String sessionKey = (String) redisTemplate.opsForValue().get(cacheKey);

        return sessionKey;
    }

    @Override
    public UserInfo info(String openId) {
        UserInfo userInfo = sysUserService.getSysUserByOpenId(openId);

        return userInfo;
    }
}
