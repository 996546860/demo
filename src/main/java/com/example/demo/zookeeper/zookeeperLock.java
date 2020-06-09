package com.example.demo.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class zookeeperLock implements InitializingBean {


    @Value("${zookeeper.connection.url}")
    private String zooUrl;

    @Value("${zookeeper.lockPath.prefix}")
    private String lockPathPrefix;

    private CuratorFramework client;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.err.println("zookeeper建立连接..");
        try {
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
            client = CuratorFrameworkFactory.newClient(zooUrl, retryPolicy);
            client.start();
        } catch (Exception e) {
            System.err.println("zookeeper 启动失败 。。");
            System.out.println(e);
            throw e;
        }

    }


    /**
     * 获取锁。返回不为null表示成功获取到锁，用完之后需要调用releaseLock方法释放
     *
     * @param lockPath    锁的相对路径，Not start with '/'
     * @param waitSeconds 等待秒数
     * @return 未获取到锁返回null
     */
    public InterProcessMutex getLock(String lockPath, int waitSeconds) {
        InterProcessMutex lock = new InterProcessMutex(client, lockPathPrefix + lockPath);
        try {
            if (lock.acquire(waitSeconds, TimeUnit.SECONDS)) {
                return lock;
            }

        } catch (Exception e) {
            System.out.println("get zookeeper lock error   ");
            System.out.println(e);
        } finally {
            releaseLock(lock);
        }

        return lock;
    }


    /**
     * 释放锁
     */
    public void releaseLock(InterProcessMutex lock) {
        if (null != lock && lock.isAcquiredInThisProcess()) {
            try {
                lock.release();
            } catch (Exception e) {
                System.out.println("remove zookeeper lock error   ");
                System.out.println(e);
            }
        }
    }
}
