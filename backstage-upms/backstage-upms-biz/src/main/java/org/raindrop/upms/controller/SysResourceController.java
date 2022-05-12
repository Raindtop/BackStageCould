

package org.raindrop.upms.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.raindrop.upms.service.SysResourceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 资源表
 *
 * @author raindrop
 * @date ${base.datetime}
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys_resource" )
@Api(value = "sys_resource", tags = "资源表管理")
public class SysResourceController {

    private final SysResourceService sysResourceService;

}
