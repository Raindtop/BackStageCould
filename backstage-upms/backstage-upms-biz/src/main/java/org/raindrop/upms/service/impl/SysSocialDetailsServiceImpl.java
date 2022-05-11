package org.raindrop.upms.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.raindrop.upms.constants.SocialDetailConstants;
import org.raindrop.upms.constants.SocialDetailEnum;
import org.raindrop.upms.entity.SysSocialDetails;
import org.raindrop.upms.mapper.SysSocialDetailsMapper;
import org.raindrop.upms.service.SysSocialDetailsService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
