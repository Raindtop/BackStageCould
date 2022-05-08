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
* 用户信息
*
* @author raindrop
* @date 2022-05-07 15:00:43
*/
@Data
@Builder
@AllArgsConstructor
@TableName(value = "sys_user")
public class SysUser extends Model<SysUser> {
    private static final long serialVersionUID=1L;
    /**
    *  用户ID
    */
    @TableId
    @ApiModelProperty("用户ID")
    private Long userId;
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
    *  是否禁用用户信息 0-否 1-是
    */
    @ApiModelProperty("是否禁用用户信息 0-否 1-是")
    private Integer locked;
    /**
    *  用户名称
    */
    @ApiModelProperty("用户名称")
    private String username;
    /**
    *  头像地址
    */
    @ApiModelProperty("头像地址")
    private String avatar;
    /**
    *  
    */
    @ApiModelProperty("")
    private String password;
    /**
    *  
    */
    @ApiModelProperty("")
    private String salt;
    /**
    *  手机号
    */
    @ApiModelProperty("手机号")
    private String phone;
    /**
    *  微信登录openId
    */
    @ApiModelProperty("微信登录openId")
    private String wxOpenid;
    /**
    *  小程序openId
    */
    @ApiModelProperty("小程序openId")
    private String miniOpenid;
    /**
    *  微信unionid
    */
    @ApiModelProperty("微信unionid")
    private String wxUnionid;
    /**
    *  0无性别 1 男 2 女
    */
    @ApiModelProperty("0无性别 1 男 2 女")
    private Integer gender;

    public SysUser () {

    }
}
