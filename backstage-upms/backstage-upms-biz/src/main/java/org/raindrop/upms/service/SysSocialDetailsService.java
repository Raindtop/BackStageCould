package org.raindrop.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.raindrop.upms.constants.SocialDetailEnum;
import org.raindrop.upms.dto.UserInfo;
import org.raindrop.upms.entity.SysSocialDetails;

/**
 * 第三方信息
 *
 * @author raindrop
 * @date ${base.datetime}
 */
public interface SysSocialDetailsService extends IService<SysSocialDetails> {

    /**
     * 获取第三方信息
     *
     * @param socialDetailEnum
     * @return
     */
    SysSocialDetails getConfig(SocialDetailEnum socialDetailEnum);

    /**
     * 根据入参查询用户信息
     *
     * @param inStr TYPE@code
     * @return
     */
    UserInfo getUserInfoBySocial(String inStr);
}
