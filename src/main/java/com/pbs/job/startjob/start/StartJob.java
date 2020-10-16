package com.pbs.job.startjob.start;

import com.pbs.job.conf.QuartzManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 定时任务的启动类 用于分布式集群部署的开发模式
 *
 */
@Configuration
public class StartJob implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(StartJob.class);
    @Autowired
    private QuartzManager quartzManager;

    public void run() {
        logger.info(">> 启动定时任务...");
        //TODO 是否需要检查pbs_qrtz_job表的运行状态的数据是否都已经设置定时任务防止遗漏
        //此处每次新增一个定时任务就修改下面这个语句(保证之前的定时任务不会在这里再次add一次)
//        quartzManager.addJob("buttonPeriodJob","pbsPeriodJobGroup","buttonPeriodJob","pbsPeriodTriggerGroup",
//                ButtonTimerJob.class,"0 0/1 * * * ?");
//        quartzManager.addJob("DivPeriodJob","pbsPeriodJobGroup","DivPeriodJob","pbsPeriodTriggerGroup",
//                DivTimerJob.class,"0 0/1 * * * ?");
        //删除定时器
//        quartzManager.removeJob("ada22de401d148618cac681625666ea6","pbsPeriodJobGroup","ada22de401d148618cac681625666ea6","pbsPeriodTriggerGroup");
        quartzManager.startJobs();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("启动定时任务......");
        run();
    }
}
