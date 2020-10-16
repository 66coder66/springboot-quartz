package com.pbs.job.test.scheduler;
import com.pbs.job.test.service.TestTaskService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**

 * @DisallowConcurrentExecution : 此标记用在实现Job的类上面,意思是不允许并发执行.
 * 注org.quartz.threadPool.threadCount的数量有多个的情况,@DisallowConcurrentExecution才生效
 */
@Service
@DisallowConcurrentExecution
public class ButtonTimerJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(ButtonTimerJob.class);

    @Autowired
    private TestTaskService testTaskService;

    /**
     *  核心方法,Quartz Job真正的执行逻辑。
     *  @throws JobExecutionException execute()方法只允许抛出JobExecutionException异常
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("--------------定时任务执行逻辑ButtonTimerJob---------------------" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        try {
            int  i = testTaskService.save();
            System.out.println("--------------定时任务执行逻辑ButtonTimerJob--------count=" +i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
