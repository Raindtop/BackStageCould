package org.raindrop.lambdaekl.basequery;

import lombok.Data;
import org.raindrop.lambdaekl.ESQueryWrapper;
import org.raindrop.lambdaekl.query.Query;

import java.util.List;

/**
 * 基础查询
 */
@Data
public class ESBaseQueryWrapper extends ESQueryWrapper {

    private List<Query> queries;
}
