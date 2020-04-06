package com.example.zkclient;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @program: com.example.zkclient.Test
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-04-05 16:46
 */
public class ZkPathCreateTest {


    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        // 1. watcher for zk
        Watcher watcherZk = new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                    System.out.println("状态：：达到同步连接；输出path的状态： path ==> " + watchedEvent.getState());
                } else {
                    System.out.println("状态没有达到同步连接的状态，暂时不作为。。。。");
                }
            }
        };

        // 2. init zk args
        ZooKeeper zk = new ZooKeeper("localhost:2182", 6000, watcherZk);
        System.out.println(zk);
        // 3. create path /controller ephemeral
        String stat = zk.create("/contr5", "hellozk".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("created path is -->: " + stat);
    }
}
