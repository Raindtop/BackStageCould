package org.raindrop.common.utils.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.*;

@Slf4j
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
    private static ExecutorService createPools(String threadNamePrefix) {
        return createPools(threadNamePrefix, new ThreadConfig(), false);
    }

    private static ExecutorService createPools(String threadNamePrefix, boolean daemon) {
        return createPools(threadNamePrefix, new ThreadConfig(), daemon);
    }

    private static ExecutorService createPools(String threadPoolName, ThreadConfig threadConfig, boolean daemon) {
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
    private static ThreadFactory createThreadFactory(String threadPoolName, boolean daemon) {
        if (threadPoolName != null) {
            return new ThreadFactoryBuilder()
                    .setNameFormat(threadPoolName + "-%d")
                    .setDaemon(daemon).build();
        }

        return Executors.defaultThreadFactory();
    }

    /**
     * shutDown 所有线程池
     */
    public static void shutDownAllThreadPool() {
        log.info("call shutDownAllThreadPool method");
        threadPools.entrySet().parallelStream().forEach(entry -> {
            ExecutorService executorService = entry.getValue();
            executorService.shutdown();
            log.info("shut down thread pool [{}] [{}]", entry.getKey(), executorService.isTerminated());
            try {
                executorService.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                log.error("Thread pool never terminated");
                executorService.shutdownNow();
            }
        });
    }

    /**
     * 打印线程池的状态
     *
     * @param threadPool 线程池对象
     */
    public static void printThreadPoolStatus(ThreadPoolExecutor threadPool) {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1, createThreadFactory("print-thread-pool-status", false));
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            log.info("============ThreadPool Status=============");
            log.info("ThreadPool Size: [{}]", threadPool.getPoolSize());
            log.info("Active Threads: [{}]", threadPool.getActiveCount());
            log.info("Number of Tasks : [{}]", threadPool.getCompletedTaskCount());
            log.info("Number of Tasks in Queue: {}", threadPool.getQueue().size());
            log.info("===========================================");
        }, 0, 1, TimeUnit.SECONDS);
    }
}
