package org.raindrop.upms.api;

import org.raindrop.upms.dto.UserInfo;
import org.raindrop.upms.entity.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "backstage-upms-biz", contextId = "remoteUserService")
public interface RemoteUserService {

    /**
     * 通过手机
     * @param name
     * @param from
     * @return
     */
    @GetMapping("/remote/user/name/{name}")
    SysUser getSysUserByName(@PathVariable("name") String name, @RequestHeader("from") String from);

    /**
     * 通过社交账号登陆
     * @param inStr TYPE@code
     * @param from
     * @return
     */
    @GetMapping("/remote/user/social/{inStr}")
    SysUser getSysUserBySocial(@PathVariable("inStr") String inStr, @RequestHeader("from") String from);


}
