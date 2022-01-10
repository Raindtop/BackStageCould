package org.raindrop.controller;

import com.alibaba.fastjson.JSONObject;
import org.raindrop.service.RouterService;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequestMapping("/router")
@RestController
public class RouterController {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private RouterService routerService;

    @PostMapping("add")
    public void addNewInfo(@RequestBody List<RouteDefinition> routeDefinition){
        redisTemplate.opsForValue().set("GATEWAY", JSONObject.toJSONString(routeDefinition), 3600, TimeUnit.SECONDS);
    }

    @PostMapping("add-re")
    public void addNewInfoRe(@RequestBody List<RouteDefinition> routeDefinition){
        redisTemplate.opsForValue().set("GATEWAY", JSONObject.toJSONString(routeDefinition), 3600, TimeUnit.SECONDS);
        routerService.refreshRouters();
    }
}
