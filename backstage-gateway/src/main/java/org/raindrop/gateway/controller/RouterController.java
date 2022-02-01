package org.raindrop.gateway.controller;

import com.alibaba.fastjson.JSONObject;
import org.raindrop.gateway.con.GatewayCon;
import org.raindrop.gateway.service.RouterService;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/router")
@RestController
public class RouterController {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private RouterService routerService;

    @PostMapping("add")
    public void addNewInfo(@RequestBody List<RouteDefinition> routeDefinition){
        redisTemplate.opsForValue().set(GatewayCon.REDIS_GATEWAY_KEY, JSONObject.toJSONString(routeDefinition));
    }

    @PostMapping("add-re")
    public void addNewInfoRe(@RequestBody List<RouteDefinition> routeDefinition){
        redisTemplate.opsForValue().set(GatewayCon.REDIS_GATEWAY_KEY, JSONObject.toJSONString(routeDefinition));
        routerService.refreshRouters();
    }
}
