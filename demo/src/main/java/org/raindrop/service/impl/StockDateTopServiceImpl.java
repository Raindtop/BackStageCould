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
package org.raindrop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.raindrop.entity.StockDateTop;
import org.raindrop.mapper.StockDateTopMapper;
import org.raindrop.service.StockDateTopService;
import org.springframework.stereotype.Service;

/**
 * 连续涨停股票
 *
 * @author raindrop
 * @date ${base.datetime}
 */
@Service
public class StockDateTopServiceImpl extends ServiceImpl<StockDateTopMapper, StockDateTop> implements StockDateTopService {

}
