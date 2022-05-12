

package org.raindrop.upms.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.raindrop.upms.service.SysRoleResourceService;
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
@RequestMapping("/sys_role_resource" )
@Api(value = "sys_role_resource", tags = "角色资源管理")
public class SysRoleResourceController {

    private final SysRoleResourceService sysRoleResourceService;

}
