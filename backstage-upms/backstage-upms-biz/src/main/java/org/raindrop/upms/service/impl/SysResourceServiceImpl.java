package org.raindrop.upms.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.raindrop.common.constants.CacheConstants;
import org.raindrop.common.constants.CommonConstants;
import org.raindrop.common.constants.enums.ResourceTypeEnum;
import org.raindrop.common.exception.ApiException;
import org.raindrop.common.utils.tree.TreeUtil;
import org.raindrop.upms.entity.SysResource;
import org.raindrop.upms.entity.SysRoleResource;
import org.raindrop.upms.mapper.SysResourceMapper;
import org.raindrop.upms.mapper.SysRoleResourceMapper;
import org.raindrop.upms.service.SysResourceService;
import org.raindrop.upms.vo.ResourceTreeVo;
import org.raindrop.upms.vo.ResourceVo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 资源表
 *
 * @author raindrop
 * @date ${base.datetime}
 */
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {

    @Resource
    private SysRoleResourceMapper sysRoleResourceMapper;

    @Override
    @Cacheable(value = CacheConstants.MENU_DETAILS, key = "#roleId", unless = "#result.isEmpty()")
    public List<ResourceVo> getResourceByRoleId(Long roleId) {
        return baseMapper.listResourceByRoleId(roleId);
    }

    @Override
    public List<ResourceTreeVo> getResourceTree(Set<ResourceVo> resourceVos, Long parentId) {
        List<ResourceTreeVo> resourceTreeVos = resourceVos.stream().filter(resourceTypePredicate()).map(ResourceTreeVo::new)
                .sorted(Comparator.comparingInt(ResourceTreeVo::getSort)).collect(Collectors.toList());
        Long parent = parentId == null ? CommonConstants.MENU_TREE_ROOT_ID : parentId;
        return TreeUtil.build(resourceTreeVos, parent);
    }

    @Override
    public List<ResourceTreeVo> resourceTree(boolean lazy, Long parentId) {
        if (!lazy) {
            return buildTree(
                    baseMapper.selectList(Wrappers.<SysResource>lambdaQuery().orderByAsc(SysResource::getSort)),
                    CommonConstants.MENU_TREE_ROOT_ID);
        }

        Long parent = parentId == null ? CommonConstants.MENU_TREE_ROOT_ID : parentId;
        return buildTree(
                baseMapper.selectList(
                        Wrappers.<SysResource>lambdaQuery().eq(SysResource::getParentId, parent).orderByAsc(SysResource::getSort)),
                parent);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstants.MENU_DETAILS, allEntries = true)
    public void deleteRosourceById(Long resourceId) {
        // 查询父节点为当前节点的节点
        List<SysResource> menuList = this.list(Wrappers.<SysResource>query().lambda().eq(SysResource::getParentId, resourceId));
        if (CollUtil.isNotEmpty(menuList)) {
            throw new ApiException("菜单有下级不能删除");
        }

        sysRoleResourceMapper.delete(Wrappers.<SysRoleResource>query().lambda().eq(SysRoleResource::getResourceId, resourceId));
        // 删除当前菜单及其子菜单
        this.removeById(resourceId);
    }

    @Override
    @CacheEvict(value = CacheConstants.MENU_DETAILS, allEntries = true)
    public void updateResourceById(SysResource sysResource) {
        this.updateById(sysResource);
    }

    /**
     * resource 资源断言查询菜单栏
     *
     * @return Predicate
     */
    private Predicate<ResourceVo> resourceTypePredicate() {
        return vo -> {
            // 其他查询菜单信息
            return ResourceTypeEnum.LEFT_MENU.getType().equals(vo.getType());
        };
    }

    /**
     * 通过SysResource创建树形节点
     *
     * @param resources
     * @param parentId
     * @return
     */
    private List<ResourceTreeVo> buildTree(List<SysResource> resources, Long parentId) {
        List<ResourceTreeVo> trees = new ArrayList<>();
        ResourceTreeVo node;
        for (SysResource resource : resources) {
            node = new ResourceTreeVo();
            node.setId(resource.getResourceId());
            node.setParentId(resource.getParentId());
            node.setName(resource.getName());
            node.setPath(resource.getPath());
            node.setPermission(resource.getPermission());
            node.setLabel(resource.getName());
            node.setIcon(resource.getIcon());
            node.setType(resource.getType());
            node.setSort(resource.getSort());
            node.setHasChildren(true);
            trees.add(node);
        }
        return TreeUtil.build(trees, parentId);
    }
}
