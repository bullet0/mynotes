package org.apache.zookeeper.aaatest;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.aaatest.util.ByteArrayUtils;

/**
 * @program: org.apache.zookeeper.aaatest.UpdateZkclient
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-03-26 17:54
 */
public class UpdateZkclient {
    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = new ZkClient("localhost:2181", 5000, 5000, new SerializableSerializer());
        zkClient.writeData("/zkclient","new date");
    }
}
