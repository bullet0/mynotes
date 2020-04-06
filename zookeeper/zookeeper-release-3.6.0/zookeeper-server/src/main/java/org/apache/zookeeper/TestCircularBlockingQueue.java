package org.apache.zookeeper;

import org.apache.zookeeper.util.CircularBlockingQueue;

/**
 * @program: org.apache.zookeeper.TestCircularBlockingQueue
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-04-03 20:57
 */
public class TestCircularBlockingQueue {
    public static void main(String[] args) {
        final CircularBlockingQueue<Integer> queue = new CircularBlockingQueue<Integer>(1);
        try {
            for (Integer i = 0; i < 100; i++) {
                queue.offer(i); // 不停地插入对象,随便插,超过界限会将旧数据丢掉
            }

            System.out.println(queue.size()); // 还是1,0-98全部丢弃了
            System.out.println(queue.take()); // 能拿到99
            System.out.println(queue.take()); // 卡住了,没数据可以拿了
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
