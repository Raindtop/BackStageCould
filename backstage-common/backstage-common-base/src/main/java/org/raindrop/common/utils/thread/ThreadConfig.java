package org.raindrop.common.utils.thread;

import lombok.Data;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.TimeUnit;

/**
 * @author raindrop
 * @date 2022/4/18
 * @Description: 多线程配置信息
 */
@Data
public class ThreadConfig {
    /**
     * 线程池默认参数
     */
    public static final int DEFAULT_CORE_POOL_SIZE = 4;
    public static final int DEFAULT_MAXIMUM_POOL_SIZE_SIZE = 8;
    private static final int DEFAULT_KEEP_ALIVE_TIME = 1;
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.MINUTES;
    private static final int DEFAULT_BLOCKING_QUEUE_CAPACITY = 100;
    private static final RejectedExecutionHandler DEFAULT_HANDLER = new DefaultRejectedHandle();

    /**
     * 可配置参数
     */
    private int corePoolSize = DEFAULT_CORE_POOL_SIZE;
    private int maximumPoolSize = DEFAULT_MAXIMUM_POOL_SIZE_SIZE;
    private long keepAliveTime = DEFAULT_KEEP_ALIVE_TIME;
    private TimeUnit unit = DEFAULT_TIME_UNIT;
    private int blockingQueueCapacity = DEFAULT_BLOCKING_QUEUE_CAPACITY;
    private RejectedExecutionHandler defaultHandler = DEFAULT_HANDLER;

    /**
     * 使用有界队列
     */
    private BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(blockingQueueCapacity);
}
