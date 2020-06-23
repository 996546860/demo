package com.example.demo.java.example.ThreadPool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: fzh
 * @Date: 2020/6/22 12:40
 * @Content: 线程池的工具类 强烈的不推荐使用
 * Executors是jdk里面提供的创建线程池的工厂类，它默认提供了4种常用的线程池应用,而不必我们去重复构造。
 * newFixedThreadPool 固定线程池
 * newCachedThreadPool 缓存线程池
 * newSingleThreadExecutor 单线程执行器
 * newScheduledThreadPool 预定线程池
 * newWorkStealingPool   窃取线程池
 * <p>
 * shutdown()    (会等待未执行完毕)启动有序关闭，在该关闭中执行先前提交的任务，该方法会等到正在执行的线程结束，但不接受任何新任务
 * shutdownNow() (不会等待未执行完毕)尝试立刻让正在运行的线程抛出 InterruptedException 异常
 * isShutdown()  返回true/false 判断是否关闭
 * awaitTermination() 阻塞等待终止结果【 timeout - 最长等待时间 单元 - timeout参数的时间单位
 * 返回： true如果此执行终止， false如果超时终止前经过
 * 抛出：InterruptedException -如果等待时发生中断 】
 */
public class Demo1_Executors {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService;
        executorService = Executors.newFixedThreadPool(10);

        executorService.execute(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(1);
        });

        List<Runnable> runnables = executorService.shutdownNow();
        executorService.shutdown();
        executorService.isShutdown();
        /*new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("发起终止");
            executorService.shutdown();
            executorService.shutdownNow();
        }).start();
        boolean termination = executorService.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println(termination);*/
    }
}
