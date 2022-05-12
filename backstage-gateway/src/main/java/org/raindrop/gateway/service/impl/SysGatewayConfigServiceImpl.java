
package org.raindrop.gateway.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.raindrop.gateway.con.GatewayCon;
import org.raindrop.gateway.entity.SysGatewayConfig;
import org.raindrop.gateway.mapper.SysGatewayConfigMapper;
import org.raindrop.gateway.service.SysGatewayConfigService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 *
 * @author raindrop
 * @date ${base.datetime}
 */
@Service
public class SysGatewayConfigServiceImpl extends ServiceImpl<SysGatewayConfigMapper, SysGatewayConfig> implements SysGatewayConfigService {
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRouter(List<SysGatewayConfig> gatewayConfigs) {
        // 删除所有老旧路由
        update(Wrappers.lambdaUpdate(SysGatewayConfig.class).set(SysGatewayConfig::getDeleted, 1));
        baseMapper.batchInsert(gatewayConfigs);

        redisTemplate.opsForValue().set(GatewayCon.REDIS_GATEWAY_KEY, JSONObject.toJSONString(gatewayConfigs));
    }

}
