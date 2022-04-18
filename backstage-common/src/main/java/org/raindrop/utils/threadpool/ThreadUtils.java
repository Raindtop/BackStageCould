package org.raindrop.utils.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Function;

public class ThreadUtils {
    /**
     * 线程池以及配置信息
     */
    private static Map<String, ExecutorService> threadPools = new ConcurrentHashMap<>();

    /**
     * 创建线程
     *
     * @return
     */
    public static ExecutorService createPoolsIfAbsent(String threadPoolName) {
        return createPoolsIfAbsent(threadPoolName, new ThreadConfig(), false);
    }


    public static ExecutorService createPoolsIfAbsent(String threadPoolName, boolean daemon) {
        return createPoolsIfAbsent(threadPoolName, new ThreadConfig(), daemon);
    }

    public static ExecutorService createPoolsIfAbsent(String threadPoolName, ThreadConfig threadConfig, boolean daemon) {
        ExecutorService threadPool = threadPools.computeIfAbsent(threadPoolName, v -> createPools(threadPoolName, threadConfig, daemon));
        // 线程被中止
        if (threadPool.isShutdown() || threadPool.isTerminated()) {
            threadPools.remove(threadPoolName);
            threadPool = createPools(threadPoolName, threadConfig, daemon);
            threadPools.put(threadPoolName, threadPool);
        }

        return threadPool;
    }

    /**
     * 创建线程池
     *
     * @return
     */
    public static ExecutorService createPools(String threadNamePrefix) {
        return createPools(threadNamePrefix, new ThreadConfig(), false);
    }

    public static ExecutorService createPools(String threadNamePrefix, boolean daemon) {
        return createPools(threadNamePrefix, new ThreadConfig(), daemon);
    }

    public static ExecutorService createPools(String threadPoolName, ThreadConfig threadConfig, boolean daemon) {
        ThreadFactory threadFactory = createThreadFactory(threadPoolName, daemon);

        ExecutorService threadPool = new ThreadPoolExecutor(threadConfig.getCorePoolSize(), threadConfig.getMaximumPoolSize(),
                threadConfig.getKeepAliveTime(), threadConfig.getUnit(), threadConfig.getWorkQueue(),
                threadFactory, new ThreadPoolExecutor.AbortPolicy());

        return threadPool;
    }

    /**
     * 创建线程工厂
     *
     * @param threadPoolName 线程前缀
     * @param daemon         是否守护线程
     * @return
     */
    public static ThreadFactory createThreadFactory(String threadPoolName, boolean daemon) {
        if (threadPoolName != null) {
            return new ThreadFactoryBuilder()
                    .setNameFormat(threadPoolName + "-%d")
                    .setDaemon(daemon).build();
        }

        return Executors.defaultThreadFactory();
    }

    public static void main(String[] args) {
        // threadPool.isShutdown() || threadPool.isTerminated() 如果线程池在这两种状态下，执行任务会出现什么情况
        ExecutorService executorService = Executors.newFixedThreadPool(1);
    }
}
