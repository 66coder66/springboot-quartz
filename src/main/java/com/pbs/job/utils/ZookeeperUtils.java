//package com.pbs.job.utils;
//
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.CuratorFrameworkFactory;
//import org.apache.curator.retry.ExponentialBackoffRetry;
//import org.apache.zookeeper.CreateMode;
//
///**
// * 暂时不用
// */
//
//public class ZookeeperUtils {
//
//    //@Value("${dubbo.registry.address}")
//    //String zkSever;
//    /**
//     * 创建节点。创建成功即获得执行权。
//     * @param strNote
//     * @throws Exception
//     */
//    public static void createNote(String strNote) throws Exception {
//        // 获取连接
//        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
//                .sessionTimeoutMs(4000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).namespace("curator").build();
//
//        curatorFramework.start();
//        try{
//            // 获取节点值
//            byte[] valNote = curatorFramework.getData().forPath("/taskLock");
//            // 节点存在时
//            if(valNote != null && valNote.length > 0){
//                // 获取节点值
//                String strVal = new String(valNote);
//                // 节点保存时间
//                long logVal = Long.parseLong(strVal);
//                // 当前时间
//                long logCurrent = DateUtils.getDateTime();
//
//                // 节点超过存活时间,删除节点
//                if(logCurrent - logVal > 1000*60*60){
//                    curatorFramework.delete().forPath("/taskLock");
//                }
//            }
//        }catch(Exception e){}
//
//        // 创建节点
//        curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath("/taskLock", String.valueOf(DateUtils.getDateTime()).getBytes());
//    }
//}
