package org.raindrop.gateway.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.*;
import java.math.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @author raindrop
 * @date 2022-02-01 16:51:42
 */
@Data
@Builder
@AllArgsConstructor
@ApiModel(value = "")
@TableName(value = "sys_gateway_config")
public class SysGatewayConfig extends Model<SysGatewayConfig> {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    @TableId
    @ApiModelProperty(value = "ID")
    private Long id;
    /**
     *
     */
    @ApiModelProperty(value = "")
    private LocalDateTime createTime;
    /**
     *
     */
    @ApiModelProperty(value = "")
    private LocalDateTime updateTime;
    /**
     * 删除标志位 0-未删除 1-已删除
     */
    @ApiModelProperty(value = "删除标志位 0-未删除 1-已删除")
    private Integer deleted;
    /**
     * 路由键
     */
    @ApiModelProperty(value = "路由键")
    private String routerId;
    /**
     * 路由名称
     */
    @ApiModelProperty(value = "路由名称")
    private String routerName;
    /**
     * 断言
     */
    @ApiModelProperty(value = "断言")
    private String predicates;
    /**
     * 拦截器
     */
    @ApiModelProperty(value = "拦截器")
    private String filter;
    /**
     * 路由地址
     */
    @ApiModelProperty(value = "路由地址")
    private String uri;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer order;

    public SysGatewayConfig() {

    }
}
