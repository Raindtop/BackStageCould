/*
 *    Copyright (c) 2018-2025, daoism All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: daoism
 */

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
