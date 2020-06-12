package com.example.demo.zookeeper;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import javax.annotation.Resource;

public abstract class zookeeperClient {


    /**
     * 至少锁60秒
     */
    private static final long LOCK_MIN_TIME = 60000;


    @Resource
    private zookeeperLock zLock;

    public void run() {
        InterProcessMutex lock = zLock.getLock(getClass().getSimpleName() + "/lock", 1);
        if (lock == null) {
            System.err.println("can not get zookeeper lock ");
            return;
        }
        long st = System.currentTimeMillis();
        System.out.println("任务开始 ... ");

        try {
            process();
        } catch (Exception e) {
            System.out.println("任务失败 ... ");
        } finally {
            long cost = System.currentTimeMillis() - st;
            System.out.println("......执行时间" + cost);

        }
    }

    public abstract void process() throws Exception;

    public static void main(String[] args) {


        /*BaseZookeeper baseZookeeper = new BaseZookeeper();
        try {
            baseZookeeper.connectZookeeper("120.79.76.167:2181");
            //baseZookeeper.createNode("/一起玩玩zookeeper","/zookeeper_is");
            //System.out.println(baseZookeeper.getChildren("/一起玩玩zookeeper"));
            //baseZookeeper.deleteNode("/zookeeper");
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /** 至少锁60秒 */




        /*CuratorFramework client;
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.newClient("120.79.76.167:2181", retryPolicy);
        client.start();*/
    }
}
