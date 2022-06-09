package org.raindrop.upms.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.raindrop.upms.constants.SocialDetailConstants;
import org.raindrop.upms.constants.SocialDetailEnum;
import org.raindrop.upms.dto.UserInfo;
import org.raindrop.upms.entity.SysSocialDetails;
import org.raindrop.upms.handle.LoginHandler;
import org.raindrop.upms.mapper.SysSocialDetailsMapper;
import org.raindrop.upms.service.SysSocialDetailsService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 角色表
 *
 * @author raindrop
 * @date ${base.datetime}
 */
@Service
public class SysSocialDetailsServiceImpl extends ServiceImpl<SysSocialDetailsMapper, SysSocialDetails> implements SysSocialDetailsService {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private Map<String, LoginHandler> loginHandlerMap;

    @Override
    public UserInfo getUserInfoBySocial(String inStr) {
        String[] inStrs = inStr.split(StringPool.AT);
        String type = inStrs[0];
        String loginStr = inStrs[1];
        return loginHandlerMap.get(type).handle(loginStr);
    }


    @Override
    public SysSocialDetails getConfig(SocialDetailEnum socialDetailEnum){
        SysSocialDetails sysSocialDetails = (SysSocialDetails) redisTemplate.opsForHash().get(SocialDetailConstants.SOCIAL_CACHE_KEY, socialDetailEnum.getType());

        if (sysSocialDetails == null){
            sysSocialDetails = baseMapper.selectOne(Wrappers.lambdaQuery(SysSocialDetails.class).eq(SysSocialDetails::getType, socialDetailEnum.getType()).last("limit 1"));

            if (sysSocialDetails != null){
                redisTemplate.opsForHash().put(SocialDetailConstants.SOCIAL_CACHE_KEY, socialDetailEnum.getType(), sysSocialDetails);
                redisTemplate.expire(SocialDetailConstants.SOCIAL_CACHE_KEY, 1, TimeUnit.DAYS);
            }
        }

        return sysSocialDetails;
    }
}
