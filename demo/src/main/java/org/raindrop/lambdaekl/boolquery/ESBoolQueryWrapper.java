package org.raindrop.lambdaekl.boolquery;

import lombok.Data;
import org.raindrop.lambdaekl.ESQueryWrapper;
import org.raindrop.lambdaekl.query.Query;

import java.util.List;

/**
 * 布尔查询
 */
@Data
public class ESBoolQueryWrapper extends ESQueryWrapper {
    private List<Query> mustQueries;

    private List<Query> mustNotQueries;

    private List<Query> shouldQueries;
}
