

package org.raindrop.upms.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.raindrop.upms.service.SysUserRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 *
 * @author raindrop
 * @date ${base.datetime}
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys_user_role" )
@Api(value = "sys_user_role", tags = "用户角色管理")
public class SysUserRoleController {

    private final SysUserRoleService sysUserRoleService;



}
