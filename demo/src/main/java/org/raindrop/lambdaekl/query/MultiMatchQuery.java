package org.raindrop.lambdaekl.query;

import lombok.Data;

import java.util.List;

/**
 * 多字段匹配查询
 */
@Data
public class MultiMatchQuery implements Query{
    /**
     * 查询内容
     */
    private Object query;

    /**
     * 匹配字段
     */
    private List<String> fields;
}
