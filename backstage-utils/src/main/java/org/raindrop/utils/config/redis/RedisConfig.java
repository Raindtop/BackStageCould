package org.raindrop.utils.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Description: TODO Redis配置信息
 * @author raindrop
 * @date 2021/12/15
 */
@Slf4j
@Configuration
public class RedisConfig {

    /**
     * @Description: TODO 生成普通的redisTemplate
     * @author raindrop
     * @date 2021/12/15
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("<-------------Init RedisTemplate config----------->");
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();

        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);
        return template;
    }

    /**
     * @Description: TODO 生成SessionRedisTemplate，session内部是一个复杂类型，所有是用jdk的序列化
     * @author raindrop
     * @date 2021/12/15
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "sessionRedisTemplate")
    public RedisTemplate sessionRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("<-------------Init SessionRedisTemplate----------->");
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
