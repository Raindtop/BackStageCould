package org.raindrop.common.security.bo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 安全工具类
 * @Author raindrop
 * @Date 2022/5/11
 **/
public class SecurityUser extends User {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    /**
     * 用户ID
     */
    @Getter
    private Integer id;

    /**
     * 手机号
     */
    @Getter
    private String phone;

    /**
     * 头像
     */
    @Getter
    private String avatar;

    /**
     * 微信小程序openid
     */
    @Getter
    private String miniOpenId;

    /**
     * 微信unionId
     */
    @Getter
    private String unionId;


    @JsonCreator
    public SecurityUser(@JsonProperty("unionId") String unionId, @JsonProperty("miniOpenId") String miniOpenId, @JsonProperty("id") Integer id,
                      @JsonProperty("phone") String phone, @JsonProperty("avatar") String avatar, @JsonProperty("username") String username,
                      @JsonProperty("password") String password, @JsonProperty("enabled") boolean enabled,
                      @JsonProperty("accountNonExpired") boolean accountNonExpired,
                      @JsonProperty("credentialsNonExpired") boolean credentialsNonExpired,
                      @JsonProperty("accountNonLocked") boolean accountNonLocked,
                      @JsonProperty("authorities") Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.miniOpenId = miniOpenId;
        this.id = id;
        this.phone = phone;
        this.avatar = avatar;
        this.unionId = unionId;
    }
}
