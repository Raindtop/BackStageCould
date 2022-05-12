

package org.raindrop.gateway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.raindrop.gateway.entity.SysGatewayConfig;

import java.util.List;

/**
 * 
 *
 * @author raindrop
 * @date 2022-02-01 16:51:42
 */
@Mapper
public interface SysGatewayConfigMapper extends BaseMapper<SysGatewayConfig> {
    int batchInsert(@Param("gatewayConfigs") List<SysGatewayConfig> gatewayConfigs);
}
