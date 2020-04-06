package org.apache.zookeeper.aaatest;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CreateBuilder;
import org.apache.curator.framework.listen.Listenable;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.RetryForever;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.Executor;

/**
 * @program: org.apache.zookeeper.aaatest.TestCuratorClient
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-03-26 17:56
 */
public class TestCuratorClient {
    public static void main(String[] args) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory
                .newClient("localhost:2181",
        5000,5000, new RetryForever(1000));
        // 这步执行完才算构建完成客户端
        curatorFramework.start();
        try {
            curatorFramework.create().withMode(CreateMode.PERSISTENT)
                    .forPath("/curator","data".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //watch
        NodeCache nodeCache = new NodeCache(curatorFramework,"/curator");
        try {
            // 默认 false ,表示刚监听上去的时候就会触发一下这个监听
            // 设置为true,表示监听之后的数据改变才会触发监听
            // 这种方式可以循环监听
            nodeCache.start(true);
            nodeCache.getListenable().addListener(new NodeCacheListener() {
                @Override
                public void nodeChanged() throws Exception {
                    System.out.println("被改了数据,删除,修改都会触发");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        // 2 这种方式是原生方法,只能监听一次,不会循环监听
//        try {
//            curatorFramework.getData().usingWatcher(new Watcher() {
//                @Override
//                public void process(WatchedEvent event) {
//                    System.out.println("另外一种监听方式");
//                }
//            }).forPath("/curator");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
