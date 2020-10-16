package com.pbs.job.conf;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 启动类不加EnableScheduling注解时，可采用此类进行相关操作
 */
@Service
public class QuartzManager {

    @Autowired
    private Scheduler scheduler;

    public QuartzManager(Scheduler scheduler){
        this.scheduler = scheduler;
    }

    /**
     * @Description: 添加一个定时任务
     *
     * @param jobName 任务名
     * @param jobGroupName  任务组名
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组名
     * @param jobClass  任务
     * @param cron   时间设置，参考quartz说明文档
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
                       Class jobClass, String cron,Map<String,String> map) {
        try {
            // 任务名，任务组，任务执行类
            JobDataMap jobDataMap = new JobDataMap(map);
            JobDetail jobDetail= JobBuilder.newJob(jobClass)
                                .withIdentity(jobName, jobGroupName)
                                .usingJobData(jobDataMap).build();
            // 触发器
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startNow();
            // 触发器时间设定
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();

            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            //判断Trigger是否已经存在
            CronTrigger existTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (existTrigger != null) {
                return;
            }
            // 调度容器设置JobDetail和Trigger
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            //if (!scheduler.isShutdown()) {
            //    scheduler.start();
            //}
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    /**
     * @Description: 修改一个任务的触发时间
     *
     * @param jobName
     * @param jobGroupName
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组名
     * @param cron   时间设置，参考quartz说明文档
     */
    public void modifyJob(String jobName,String jobGroupName, String triggerName, String triggerGroupName,
                          Class jobClass, String cron,Map<String,String> map) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass,cron,map);
                return;
            }

            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                System.out.println("任务："+jobName+"被修改");
                /** 方式一 ：调用 rescheduleJob 开始 */
               /* // 触发器
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                // 触发器名,触发器组
                triggerBuilder.withIdentity(triggerName, triggerGroupName);
                triggerBuilder.startNow();
                // 触发器时间设定
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
                // 创建Trigger对象
                trigger = (CronTrigger) triggerBuilder.build();
                // 方式一 ：修改一个任务的触发时间
                scheduler.rescheduleJob(triggerKey, trigger);*/
                /** 方式一 ：调用 rescheduleJob 结束 */
                /** 方式二：先删除，然后在创建一个新的Job  */
                //JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroupName));
                //Class<? extends Job> jobClass = jobDetail.getJobClass();
                removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
                addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass,cron,map);
                /** 方式二 ：先删除，然后在创建一个新的Job */
            }else{
                resumeJob(jobName,jobGroupName);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * @Description: 移除一个任务
     *
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     */
    public  void removeJob(String jobName, String jobGroupName,String triggerName, String triggerGroupName) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);

            scheduler.pauseTrigger(triggerKey);// 停止触发器
            scheduler.unscheduleJob(triggerKey);// 移除触发器
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * @Description: 停止一个触发器
     *
     * @param triggerName
     * @param triggerGroupName
     */
    public  void pauseTrigger(String triggerName, String triggerGroupName) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            scheduler.pauseTrigger(triggerKey);// 停止触发器
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * @Description: 恢复一个触发器
     *
     * @param triggerName
     * @param triggerGroupName
     */
    public  void resumeTrigger(String triggerName, String triggerGroupName) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            scheduler.resumeTrigger(triggerKey);// 停止触发器
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * @Description: 停止一个任务
     *
     * @param jobName
     * @param jobGroupName
     */
    public  void pauseJob(String jobName, String jobGroupName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName,jobGroupName);
            scheduler.pauseJob(jobKey);// 停止任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * @Description: 恢复一个任务
     *
     * @param jobName
     * @param jobGroupName
     */
    public  void resumeJob(String jobName, String jobGroupName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName,jobGroupName);
            scheduler.resumeJob(jobKey);// 恢复任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * @Description:启动单个定时任务
     */
    public  void startOneJob(String jobName, String jobGroupName,String triggerName, String triggerGroupName) {
        try {
            JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroupName));
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * @Description:启动所有定时任务
     */
    public void startJobs() {
        try {
            scheduler.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description:关闭所有定时任务
     */
    public void shutdownJobs() {
        try {
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取当前正在执行的任务
     * @return
     */
    public boolean getCurrentJobs(String name){
        try {
            List<JobExecutionContext> jobContexts = scheduler.getCurrentlyExecutingJobs();
            for (JobExecutionContext context : jobContexts) {
                if (name.equals(context.getTrigger().getJobKey().getName())) {
                    return true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
