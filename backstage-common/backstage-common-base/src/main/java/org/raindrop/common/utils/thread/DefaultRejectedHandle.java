package org.raindrop.common.utils.thread;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author raindrop
 * @date 2022/4/18
 * @Description: 默认多线程拒绝策略
 */
@Slf4j
public class DefaultRejectedHandle implements RejectedExecutionHandler {

    public DefaultRejectedHandle() {

    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        log.error("Task {} rejected from {}", r.toString(), executor.toString());
    }
}
