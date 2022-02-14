package org.raindrop.gateway.bean;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.raindrop.gateway.con.GatewayCon;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<RouteDefinition> routeDefinitions = getRouterDefinitions();
        return Flux.fromIterable(routeDefinitions);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(obj ->{
            List<RouteDefinition> routeDefinitions = getRouterDefinitions();
            routeDefinitions.add(obj);
            redisTemplate.opsForValue().set(GatewayCon.REDIS_GATEWAY_KEY, JSONObject.toJSONString(routeDefinitions), 3600, TimeUnit.SECONDS);
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id ->{
            List<RouteDefinition> oldRoute = JSONObject.parseArray((String)redisTemplate.opsForValue().get("GATEWAY"), RouteDefinition.class);
            List<RouteDefinition> newRoute = oldRoute.stream().filter(route -> !route.getId().equals(id)).collect(Collectors.toList());
            redisTemplate.opsForValue().set(GatewayCon.REDIS_GATEWAY_KEY, JSONObject.toJSONString(newRoute), 3600, TimeUnit.SECONDS);
            return Mono.empty();
        });
    }

    public List<RouteDefinition> getRouterDefinitions(){
        return JSONObject.parseArray((String)redisTemplate.opsForValue().get(GatewayCon.REDIS_GATEWAY_KEY), RouteDefinition.class);
    }
}
