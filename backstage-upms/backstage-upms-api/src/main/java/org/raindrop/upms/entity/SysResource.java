package org.raindrop.upms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.*;


/**
* 资源表
*
* @author raindrop
* @date 2022-05-07 15:00:43
*/
@Data
@Builder
@AllArgsConstructor
@TableName(value = "sys_resource")
public class SysResource extends Model<SysResource> {
    private static final long serialVersionUID=1L;
    /**
    *  
    */
    @TableId
    @ApiModelProperty("")
    private Long resourceId;
    /**
    *  创建时间
    */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
    /**
    *  最后修改时间
    */
    @ApiModelProperty("最后修改时间")
    private LocalDateTime updateTime;
    /**
    *  删除标志位 0-未删除 1-已删除
    */
    @ApiModelProperty("删除标志位 0-未删除 1-已删除")
    private Integer deleted;
    /**
    *  资源类型 0-未知 10-菜单 20-按钮
    */
    @ApiModelProperty("资源类型 0-未知 10-菜单 20-按钮")
    private Integer  type;
    /**
    *  资源名称
    */
    @ApiModelProperty("资源名称")
    private String name;
    /**
    *  权限
    */
    @ApiModelProperty("权限")
    private String permission;
    /**
    *  资源路径
    */
    @ApiModelProperty("资源路径")
    private String path;
    /**
    *  父菜单ID
    */
    @ApiModelProperty("父菜单ID")
    private Long parentId;
    /**
    *  图标
    */
    @ApiModelProperty("图标")
    private String icon;
    /**
    *  排序值
    */
    @ApiModelProperty("排序值")
    private Integer  sort;

    public SysResource () {

    }
}
