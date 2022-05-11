package org.raindrop.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.raindrop.upms.constants.SocialDetailEnum;
import org.raindrop.upms.dto.UserInfo;
import org.raindrop.upms.entity.SysSocialDetails;
import org.raindrop.upms.entity.SysUser;

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
}
