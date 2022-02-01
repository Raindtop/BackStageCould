package org.raindrop.lambdaekl.query;

import lombok.Data;

import java.util.List;

/**
 * 精确匹配查询
 */
@Data
public class TermsQuery implements Query{
    /**
     * 列
     */
    private String filed;

    /**
     * 查询内容
     */
    private List<Object> query;
}
