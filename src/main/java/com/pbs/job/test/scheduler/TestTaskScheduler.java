//package com.pbs.job.test.scheduler;
//
//
//import com.pbs.job.test.service.TestTaskService;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class TestTaskScheduler {
//
//    @Resource
//    TestTaskService taskService;
//
//    // 每2分钟执行一次,cron表达式请自行按照实际需求设置
//    //@Scheduled(cron = "0 0/2 * * * ?")
//    public void oneMinuteJob(){
//        System.out.println("now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        Map<String,Object> param = new HashMap<>();
//        int i = taskService.save();
//        System.out.println(i);
//    }
//
//}
