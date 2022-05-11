package org.raindrop.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.raindrop.upms.entity.SysResource;
import org.raindrop.upms.vo.ResourceVo;

import java.util.List;

/**
 * 资源表
 *
 * @author raindrop
 * @date 2022-05-07 14:16:41
 */
@Mapper
public interface SysResourceMapper extends BaseMapper<SysResource> {
    /**
     * 根据角色ID查询资源信息
     *
     * @param roleId
     * @return
     */
    List<ResourceVo> listResourceByRoleId(@Param("roleId") Long roleId);
}
