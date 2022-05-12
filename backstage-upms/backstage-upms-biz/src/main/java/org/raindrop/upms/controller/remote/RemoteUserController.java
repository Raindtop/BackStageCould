package org.raindrop.upms.controller.remote;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.raindrop.upms.dto.UserInfo;
import org.raindrop.upms.service.SysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/remote/user")
@Api(value = "前端忽略 内部User接口信息")
public class RemoteUserController {
    private final SysUserService sysUserService;

    /**
     * 通过名称 查询用户信息
     *
     * @param name
     * @return
     */
    @GetMapping("/name/{name}")
    public UserInfo getUserByName(@PathVariable("name") String name) {

        return sysUserService.getSysUserByName(name);
    }

    /**
     * 通过社交账号登陆
     *
     * @param inStr
     * @return
     */
    @GetMapping("/social/{inStr}")
    public UserInfo getUserBySocial(@PathVariable("inStr") String inStr) {
        return sysUserService.getUserInfoBySocial(inStr);
    }


}
