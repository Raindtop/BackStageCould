

package org.raindrop.upms.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.raindrop.upms.service.SysRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 角色表
 *
 * @author raindrop
 * @date ${base.datetime}
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys_role" )
@Api(value = "sys_role", tags = "角色表管理")
public class SysRoleController {

    private final SysRoleService sysRoleService;

}
