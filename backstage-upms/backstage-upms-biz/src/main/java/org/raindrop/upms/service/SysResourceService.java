package org.raindrop.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.raindrop.upms.entity.SysResource;
import org.raindrop.upms.vo.ResourceTreeVo;
import org.raindrop.upms.vo.ResourceVo;

import java.util.List;
import java.util.Set;

/**
 * 资源表
 *
 * @author raindrop
 * @date ${base.datetime}
 */
public interface SysResourceService extends IService<SysResource> {

    /**
     * 根据角色查询资源信息
     *
     * @param roleId
     * @return
     */
    List<ResourceVo> getResourceByRoleId(Long roleId);

    /**
     * 获取资源树形结构
     *
     * @param resourceVos
     * @param parentId
     * @return
     */
    List<ResourceTreeVo> getResourceTree(Set<ResourceVo> resourceVos, Long parentId);

    /**
     * 构建树查询 1. 不是懒加载情况，查询全部 2. 是懒加载，根据parentId 查询 2.1 父节点为空，则查询ID -1
     *
     * @param lazy     是否是懒加载
     * @param parentId 父节点ID
     * @return
     */
    List<ResourceTreeVo> resourceTree(boolean lazy, Long parentId);

    /**
     * 删除资源信息
     *
     * @param resourceId
     */
    void deleteRosourceById(Long resourceId);

    /**
     * 更新资源信息
     *
     * @param sysResource
     */
    void updateResourceById(SysResource sysResource);
}
