/*
 *    Copyright (c) 2018-2025, daoism All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: daoism
 */

package org.raindrop.upms.controller;

import org.raindrop.upms.service.SysUserRoleService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


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
