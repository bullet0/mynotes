package org.apache.zookeeper.aaatest;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.aaatest.util.ByteArrayUtils;

import java.io.UnsupportedEncodingException;

/**
 * @program: org.apache.zookeeper.aaatest.TestZkClient
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-03-26 17:22
 */
public class TestZkClient {
    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = new ZkClient("localhost:2181",5000,5000,new SerializableSerializer());
        zkClient.createPersistent("/zkclient", ByteArrayUtils.objectToBytes("data"));

        Object o = zkClient.readData("/zkclient");
        System.out.println(ByteArrayUtils.bytesToObject((byte[])o));
//        Object o1 = zkClient.readData("/zkclient2");
//        try {
//            System.out.println(new String((byte[])o1,"utf-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        zkClient.subscribeDataChanges("/zkclient", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("数据被改变了 : "+ s + "\t" + o);
            }

            @Override
            public void handleDataDeleted(String path) throws Exception {
                System.out.println("数据被删了 : " + path);
            }
        });
        Thread.currentThread().join();
    }
}
