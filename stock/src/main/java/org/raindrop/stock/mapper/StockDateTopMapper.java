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

package org.raindrop.stock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.raindrop.stock.entity.StockDateTop;
import org.apache.ibatis.annotations.Mapper;

/**
 * 连续涨停股票
 *
 * @author raindrop
 * @date 2021-12-23 14:18:30
 */
@Mapper
public interface StockDateTopMapper extends BaseMapper<StockDateTop> {

}
