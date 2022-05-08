package org.raindrop.upms.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ResourceVo {

    /**
     *
     */
    @ApiModelProperty("")
    private Long resourceId;
    /**
     *  删除标志位 0-未删除 1-已删除
     */
    /**
     * 资源类型 0-未知 10-菜单 20-按钮
     */
    @ApiModelProperty("资源类型 0-未知 10-菜单 20-按钮")
    private Integer type;
    /**
     * 资源名称
     */
    @ApiModelProperty("资源名称")
    private String name;
    /**
     * 权限
     */
    @ApiModelProperty("权限")
    private String permission;
    /**
     * 资源路径
     */
    @ApiModelProperty("资源路径")
    private String path;
    /**
     * 父菜单ID
     */
    @ApiModelProperty("父菜单ID")
    private long parentId;
    /**
     * 图标
     */
    @ApiModelProperty("图标")
    private String icon;
    /**
     * 排序值
     */
    @ApiModelProperty("排序值")
    private Integer sort;
}
