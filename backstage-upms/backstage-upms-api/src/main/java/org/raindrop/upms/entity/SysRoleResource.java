package org.raindrop.upms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.*;


/**
* 
*
* @author raindrop
* @date 2022-05-07 15:00:43
*/
@Data
@Builder
@AllArgsConstructor
@TableName(value = "sys_role_resource")
public class SysRoleResource extends Model<SysRoleResource> {
    private static final long serialVersionUID=1L;
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

    @ApiModelProperty("")
    private Long roleId;

    @ApiModelProperty("")
    private Long resourceId;

    public SysRoleResource () {

    }
}
