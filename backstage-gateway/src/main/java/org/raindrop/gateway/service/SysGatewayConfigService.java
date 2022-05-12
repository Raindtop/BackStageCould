

package org.raindrop.gateway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.raindrop.gateway.entity.SysGatewayConfig;

import java.util.List;

/**
 * @author raindrop
 * @date ${base.datetime}
 */
public interface SysGatewayConfigService extends IService<SysGatewayConfig> {
    /**
     * 修改路由配置信息
     *
     * @param gatewayConfigs
     */
    void updateRouter(List<SysGatewayConfig> gatewayConfigs);
}
