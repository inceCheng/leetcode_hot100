package com.chg.other;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 企业级线程池最佳实践演示
 */
public class EnterpriseThreadPoolDemo {

    public static void main(String[] args) {
        System.out.println("--- 系统启动，初始化业务线程池 ---");
        
        // 1. 【最佳实践】绝不使用 Executors，而是通过 ThreadPoolExecutor 显式创建
        // 2. 【最佳实践】赋予线程池业务名称前缀，方便日志排查
        ThreadFactory namedThreadFactory = new BusinessThreadFactory("order-processor");

        // 3. 【最佳实践】自定义线程池，重写 afterExecute 以实现全局异常捕获
        ThreadPoolExecutor businessPool = new BusinessThreadPoolExecutor(
                2,                      // corePoolSize: 核心线程数 2
                5,                      // maximumPoolSize: 最大线程数 5
                60L,                    // keepAliveTime: 空闲存活时间 60秒
                TimeUnit.SECONDS,       // unit: 时间单位
                new ArrayBlockingQueue<>(10), // workQueue: 有界阻塞队列，容量 10（坚决不用无界队列）
                namedThreadFactory,     // threadFactory: 自定义线程工厂
                new ThreadPoolExecutor.CallerRunsPolicy() // handler: 拒绝策略（队列满且达最大线程时，退回给主线程执行）
        );

        // 4. 【最佳实践】注册优雅停机钩子 (模拟 Spring 的 @PreDestroy)
        registerGracefulShutdown(businessPool);

        System.out.println("--- 开始模拟高并发提交任务 ---");
        
        // 模拟提交 20 个任务 (核心2 + 队列10 + 临时3 = 15。剩下 5 个会触发拒绝策略)
        for (int i = 1; i <= 20; i++) {
            final int taskId = i;
            
            // 推荐使用 execute 而不是 submit，除非你需要 Future 返回值
            businessPool.execute(() -> {
                // 模拟业务执行时间
                try {
                    Thread.sleep(500); 
                    System.out.println(Thread.currentThread().getName() + " 成功执行完任务: " + taskId);
                    
                    // 5. 【最佳实践】模拟任务内部抛出未受检异常的“隐形炸弹”
                    if (taskId == 7) {
                        throw new RuntimeException("任务 7 遭遇了严重的业务异常数据！");
                    }
                } catch (InterruptedException e) {
                    // 响应中断信号（优雅停机时会发送中断信号）
                    System.err.println(Thread.currentThread().getName() + " 被中断，停止执行任务 " + taskId);
                    Thread.currentThread().interrupt(); 
                }
            });
        }

        System.out.println("--- 主线程任务提交完毕，主线程即将结束 ---");
        // 主线程结束，稍作等待让任务跑一会儿，然后触发系统退出操作（测试优雅停机）
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        System.exit(0); // 正常退出 JVM，触发 Shutdown Hook
    }

    // ================= 以下为底层支撑类的实现 =================

    /**
     * 支撑组件 1：自定义线程工厂，用于给线程命名
     */
    static class BusinessThreadFactory implements ThreadFactory {
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        public BusinessThreadFactory(String businessName) {
            this.namePrefix = businessName + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, namePrefix + threadNumber.getAndIncrement());
            // 如果需要，这里可以设置为守护线程 t.setDaemon(false);
            // 如果需要，这里可以设置优先级 t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    /**
     * 支撑组件 2：增强型线程池，提供统一异常兜底
     */
    static class BusinessThreadPoolExecutor extends ThreadPoolExecutor {
        
        public BusinessThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                          BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        // 统一捕获 execute() 或 submit() 运行过程中的未受检异常
        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            
            // 如果是使用 submit 提交的，异常会被包装在 Future 中，需要用特殊方式取出
            if (t == null && r instanceof Future<?>) {
                try {
                    Object result = ((Future<?>) r).get();
                } catch (CancellationException ce) {
                    t = ce;
                } catch (ExecutionException ee) {
                    t = ee.getCause();
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt(); // 忽略/重置中断状态
                }
            }
            
            // 统一日志处理
            if (t != null) {
                System.err.println("[警报] 线程池拦截到未捕获的异常: 线程=" + Thread.currentThread().getName() + ", 错误原因: " + t.getMessage());
                // 真实项目中，这里通常会接入监控报警系统（发钉钉/邮件等）
            }
        }
    }

    /**
     * 支撑组件 3：优雅停机机制
     */
    private static void registerGracefulShutdown(ExecutorService executorPool) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\n*** 收到系统关闭信号，准备优雅关闭业务线程池 ***");
            
            // 1. 拒绝新任务，但继续执行队列中已有的任务
            executorPool.shutdown();
            
            try {
                // 2. 给定一个缓冲时间（例如 30 秒），让现有任务执行完
                System.out.println("*** 等待存量任务执行完毕 (最多等待 30 秒)... ***");
                if (!executorPool.awaitTermination(30, TimeUnit.SECONDS)) {
                    // 3. 超时依然没有执行完，强制中断正在执行的线程
                    System.err.println("*** 线程池关闭超时，强制中断仍在执行的线程 ***");
                    executorPool.shutdownNow();
                } else {
                    System.out.println("*** 线程池内任务已全部平滑处理完毕 ***");
                }
            } catch (InterruptedException ie) {
                // 捕获异常，强制关闭
                executorPool.shutdownNow();
                Thread.currentThread().interrupt();
            }
            System.out.println("*** 业务线程池已彻底销毁 ***");
        }));
    }
}