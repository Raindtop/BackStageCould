package org.raindrop.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.raindrop.upms.entity.SysResource;
import org.raindrop.upms.entity.SysRoleResource;

import java.util.List;

/**
 * @author raindrop
 * @date 2022-05-07 14:16:41
 */
@Mapper
public interface SysRoleResourceMapper extends BaseMapper<SysRoleResource> {
    /**
     * 根据角色Ids 查询菜单权限
     *
     * @param ids
     * @return
     */
    List<SysResource> getResourceByRoleIds(@Param("ids") List<Integer> ids);
}
