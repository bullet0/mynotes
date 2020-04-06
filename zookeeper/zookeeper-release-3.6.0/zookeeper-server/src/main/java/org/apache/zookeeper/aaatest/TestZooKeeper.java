package org.apache.zookeeper.aaatest;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @program: org.apache.zookeeper.aaatest.Test
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-03-26 15:24
 */
public class TestZooKeeper {
    public static void main(String[] args) throws InterruptedException {
        try {
            Watcher defaultWatcher = (watchedEvent)->{
                System.out.println("默认的watcher被触发"+watchedEvent);
            };
            ZooKeeper zooKeeper = new ZooKeeper("localhost:2181",5000,null);
            Stat stat = new Stat();
            Watcher watcher = (watchedEvent) -> {
                System.out.println("/test上的watch");
            };
            byte[] data = zooKeeper.getData("/test", watcher , stat);
            System.out.println("数据为 : " + new String(data));
            System.out.println("stat : "+ stat);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }

        Thread.currentThread().join();
    }
}
