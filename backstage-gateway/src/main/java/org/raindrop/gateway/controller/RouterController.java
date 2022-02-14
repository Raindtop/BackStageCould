package org.raindrop.gateway.controller;

import org.raindrop.gateway.bean.MyApplicationEventPublisherAware;
import org.raindrop.gateway.entity.SysGatewayConfig;
import org.raindrop.gateway.service.SysGatewayConfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/router")
public class RouterController {
    @Resource
    private SysGatewayConfigService sysGatewayConfigService;
    @Resource
    private MyApplicationEventPublisherAware myApplicationEventPublisherAware;

    @PostMapping("update")
    public void update(@RequestBody List<SysGatewayConfig> configs){
        sysGatewayConfigService.updateRouter(configs);
        myApplicationEventPublisherAware.refreshRouters();
    }
}
