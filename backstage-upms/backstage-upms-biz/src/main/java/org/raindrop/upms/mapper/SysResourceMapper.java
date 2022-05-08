/*
 *    Copyright (c) 2018-2025, daoism All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: daoism
 */

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
