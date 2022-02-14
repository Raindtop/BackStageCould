package org.raindrop.lambdaekl.common.query;

import lombok.Data;
import org.raindrop.lambdaekl.common.query.Query;

import java.util.List;

/**
 * 精确匹配查询
 * see <a hre="https://www.elastic.co/guide/en/elasticsearch/client/net-api/current/terms-set-query-usage.html">Document</a>
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
