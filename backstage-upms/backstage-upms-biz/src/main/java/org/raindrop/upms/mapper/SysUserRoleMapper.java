package org.raindrop.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.raindrop.upms.entity.SysRole;
import org.raindrop.upms.entity.SysUserRole;

import java.util.List;

/**
 * @author raindrop
 * @date 2022-05-07 14:16:41
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    /**
     * 查询用户角色
     *
     * @param userId
     * @return
     */
    List<SysRole> getRoleByUserId(@Param("userId") Integer userId);
}
