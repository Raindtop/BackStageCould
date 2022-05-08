package org.raindrop.upms.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.raindrop.upms.entity.SysUser;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @Author raindrop
 * @Date 2022/5/8
 **/
@Data
@ApiModel(value = "用户信息")
public class UserInfo implements Serializable {

    /**
     * 用户基本信息
     */
    @ApiModelProperty(value = "用户基本信息")
    private SysUser sysUser;

    /**
     * 权限标识集合
     */
    @ApiModelProperty(value = "权限标识集合")
    private String[] permissions;

    /**
     * 角色集合
     */
    @ApiModelProperty(value = "角色标识集合")
    private Integer[] roles;

}
