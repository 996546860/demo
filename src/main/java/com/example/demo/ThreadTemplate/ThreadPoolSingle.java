package com.example.demo.ThreadTemplate;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: fzh
 * @Date: 2020/9/17 12:09
 * @Content:
 */
@Slf4j
public class ThreadPoolSingle {

    static {
        System.out.println(HandleThread.POLLNAME + "创建成功");
        log.info("启动监听线程任务");
        notifyThread();
        log.info("启动监听线程任务成功");
    }

    private ThreadPoolSingle() {
        System.err.println("禁止禁止");
    }

    /**
     * add        增加一个元索                     如果队列已满，则抛出一个IIIegaISlabEepeplian异常
     * 　　remove   移除并返回队列头部的元素    如果队列为空，则抛出一个NoSuchElementException异常
     * 　　element  返回队列头部的元素             如果队列为空，则抛出一个NoSuchElementException异常
     * 　　offer       添加一个元素并返回true       如果队列已满，则返回false
     * 　　poll         移除并返问队列头部的元素    如果队列为空，则返回null
     * 　　peek       返回队列头部的元素             如果队列为空，则返回null
     * 　　put         添加一个元素                      如果队列满，则阻塞
     * 　　take        移除并返回队列头部的元素     如果队列为空，则阻塞
     *
     * @param object
     * @return
     */
    protected static Boolean addEvent(Object object) {
        try {
            HandleThread.queue.put(object);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            log.error("增加任务失败: " + e.getMessage());
            return false;
        }
        return true;
    }

    protected static void notifyThread() {
        new Thread(() -> {
            while (true) {
                Object ex = null;
                try {
                    ex = HandleThread.queue.take();
                    log.info(Thread.currentThread().getName() + "监听到一个任务");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ex instanceof Runnable) {
                    executeRunnable((Runnable) ex);
                } else if (ex instanceof FutureTask) {
                    executeFutures((FutureTask) ex);
                }
            }
        }, "线程暂存队列").start();
    }

    private static void executeRunnable(Runnable runnable) {
        HandleThread.doExecute().execute(runnable);
    }

    private static void executeFutures(FutureTask futureTask) {
        HandleThread.doExecute().execute(futureTask);
    }

    private static class HandleThread {
        static QhTreadFactory treadFactory = new QhTreadFactory();
        static MyIgnorePolicy myignorepolicy = new MyIgnorePolicy();
        private static String POLLNAME = "麻痹线程池";
        /*** cpu线程数**/
        private static int CPU_THREAD_NUM = Runtime.getRuntime().availableProcessors() << 1;
        /**
         * 启航要的线程数
         **/
        private static int MAX_THREAD_NUM = 20;
        /*** 最大线程池**/
        private static int THREAD_NUM = MAX_THREAD_NUM > CPU_THREAD_NUM ? CPU_THREAD_NUM : MAX_THREAD_NUM;
        /*** 核心线程池**/
        private static int THREAD_CORE_NUM = 10;
        /***线程可以活多久*/
        private static long KEEP_ALIVE_TIME = 5L;
        static ExecutorService executorService = new ThreadPoolExecutor(THREAD_CORE_NUM, THREAD_NUM,
                KEEP_ALIVE_TIME, TimeUnit.SECONDS, new SynchronousQueue<>(), treadFactory, myignorepolicy);
        private static ArrayBlockingQueue queue = new ArrayBlockingQueue<>(THREAD_NUM);

        private HandleThread() {
        }

        private static ExecutorService doExecute() {
            ThreadPoolExecutor tpe = (ThreadPoolExecutor) HandleThread.executorService;
            while ((tpe.getActiveCount() - THREAD_NUM) == 0) {
                log.error("线程没有得到线程->当前线程池满了，等待执行中....");
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return executorService;
        }
    }

    /**
     * 线程工厂，给每个线程一个名字
     **/
    private static class QhTreadFactory implements ThreadFactory {

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "qh-thread-" + mThreadNum.getAndIncrement());
            System.out.println(t.getName() + " has been created");
            return t;
        }
    }

    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            /** 线程池满了之后会 执行拒绝策略**/
            System.err.println(r.toString() + " 线程池暂时无法执行，返回");
        }
    }
}
