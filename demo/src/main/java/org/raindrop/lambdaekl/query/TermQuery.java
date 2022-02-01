package org.raindrop.lambdaekl.query;

import lombok.Data;

/**
 * 精确匹配查询
 */
@Data
public class TermQuery implements Query{
    /**
     * 列
     */
    private String filed;

    /**
     * 查询内容
     */
    private Object query;
}
