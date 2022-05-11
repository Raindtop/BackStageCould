package org.raindrop.gateway.bean;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.raindrop.gateway.con.GatewayCon;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class CreateRouteInRedis {
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 创建RouteDefine信息存入Redis
     */
    @PostConstruct
    public void create(){
        RouteDefinition definition = new RouteDefinition();
        definition.setId(IdUtil.simpleUUID());
        URI uri = UriComponentsBuilder.fromUriString("lb://backstage-upms-biz").build().toUri();
        definition.setUri(uri);
        PredicateDefinition predicate = new PredicateDefinition();
        predicate.setName("Path");

        Map<String, String> predicateParams = new HashMap<>(8);
        predicateParams.put("pattern", "/upms/**");
        predicate.setArgs(predicateParams);

        definition.setPredicates(Arrays.asList(predicate));


        String json = JSON.toJSONString(Arrays.asList(definition));
        log.info("Create RouteDefinition. RouteDefinitionList={}", json);
        redisTemplate.opsForValue().set(GatewayCon.REDIS_GATEWAY_KEY, json);
    }

}
