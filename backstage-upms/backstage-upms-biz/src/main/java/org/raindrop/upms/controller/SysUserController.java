

package org.raindrop.upms.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.raindrop.upms.service.SysUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户信息
 *
 * @author raindrop
 * @date ${base.datetime}
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys_user" )
@Api(value = "sys_user", tags = "用户信息管理")
public class SysUserController {

    private final SysUserService sysUserService;

}
