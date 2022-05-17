package org.raindrop.upms.api;

import org.raindrop.common.constants.SecurityConstants;
import org.raindrop.upms.dto.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "backstage-upms-biz", contextId = "remoteUserService")
public interface RemoteUserService {

    /**
     * 通过名称 查询用户信息
     *
     * @param name
     * @param from
     * @return
     */
    @GetMapping("/remote/user/name/{name}")
    UserInfo getSysUserByName(@PathVariable("name") String name, @RequestHeader(SecurityConstants.FROM) String from);

    /**
     * 通过社交账号登陆
     *
     * @param inStr TYPE@code
     * @param from
     * @return
     */
    @GetMapping("/remote/user/social/{inStr}")
    UserInfo getSysUserBySocial(@PathVariable("inStr") String inStr, @RequestHeader(SecurityConstants.FROM) String from);


}
