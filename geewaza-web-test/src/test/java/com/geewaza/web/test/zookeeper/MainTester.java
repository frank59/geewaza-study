package com.geewaza.web.test.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by WangHeng on 2016/4/21.
 */
public class MainTester {

    private static final String VM_HOST = "192.168.11.128:2181";

    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        test01();
        System.out.println("OK!");
        System.exit(0);
    }

    private static void test01() throws KeeperException, InterruptedException, IOException {
        ZooKeeper zk = new ZooKeeper(VM_HOST, 500000,new Watcher() {
            // 监控所有被触发的事件
            public void process(WatchedEvent event) {
                //dosomething
            }
        });
        //创建一个节点root，数据是mydata,不进行ACL权限控制，节点为永久性的(即客户端shutdown了也不会消失)
//        zk.create("/root", "mydata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        //在root下面创建一个childone znode,数据为childone,不进行ACL权限控制，节点为永久性的
        zk.create("/root/childone","childone".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);

        //取得/root节点下的子节点名称,返回List<String>
        List<String> names = zk.getChildren("/root",true);
        System.out.println("names=" + names);

        //取得/root/childone节点下的数据,返回byte[]
        byte[] dataByte = zk.getData("/root/childone", true, null);
        System.out.println("dataByte=" + new String(dataByte));

        //修改节点/root/childone下的数据，第三个参数为版本，如果是-1，那会无视被修改的数据版本，直接改掉
        zk.setData("/root/childone","childonemodify".getBytes(), -1);

        //删除/root/childone这个节点，第二个参数为版本，－1的话直接删除，无视版本
//        zk.delete("/root/childone", -1);

        //关闭session
        zk.close();

    }
}
