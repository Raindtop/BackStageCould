package org.raindrop.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.raindrop.upms.entity.SysUser;

/**
 * 用户信息
 *
 * @author raindrop
 * @date 2022-05-07 14:16:41
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}
