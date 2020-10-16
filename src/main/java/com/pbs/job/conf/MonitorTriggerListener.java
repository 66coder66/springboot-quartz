package com.pbs.job.conf;

import com.pbs.job.log.model.QuartzLog;
import com.pbs.job.log.service.JobLogService;
import com.pbs.job.utils.SpringContextUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义监听器  写入日志表
 */
public class MonitorTriggerListener implements TriggerListener {

    private Logger logger = LoggerFactory.getLogger(MonitorTriggerListener.class);
    private ThreadLocal<QuartzLog> localLog=new ThreadLocal<QuartzLog>();

    @Override
    public String getName() {
        return "MonitorTriggerListener";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {
        try {
            JobDetail jobDetail = jobExecutionContext.getJobDetail();
            JobDataMap jobDataMap = jobDetail.getJobDataMap();
            Map<String, Object> param = new HashMap<>();
            LocalDateTime now = LocalDateTime.now();
            param.put("createDate",now);
            param.put("jobId",jobDetail.getKey().toString());
            param.put("jobName",jobDataMap.get("JOB_DESCP"));
            param.put("jobGroup","pbsPeriodJobGroup");
            param.put("methodName","execute");
            String jobImp = jobDataMap.get("JOB_IMP").toString().replaceAll("\r|\n","");
            if(jobImp.length()>250){
                jobImp = jobImp.substring(0,240);
            }
            param.put("methodParams",jobImp);
            param.put("excuteTime",jobExecutionContext.getJobRunTime());
            param.put("jobMsg",jobDetail.getDescription());
            TriggerKey triggerKey = jobExecutionContext.getTrigger().getKey();
            param.put("jobStatus",jobExecutionContext.getScheduler().getTriggerState(triggerKey));
            param.put("excepMsg",jobExecutionContext.getResult());
            System.out.println(localLog.get());
            QuartzLog quartzLog = getJobLogService().insertData(param);
            localLog.set(quartzLog);
            System.out.println(quartzLog);
        } catch (Exception e) {
            logger.error("记录job开始时间异常",e);
        }catch (Throwable e) {
            logger.error("记录job开始时间出错",e);
        }
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {

    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jec, Trigger.CompletedExecutionInstruction cei) {
        try {
            QuartzLog quartzLog = localLog.get();
            if(quartzLog == null) return ;
            //FIXME 下面的数据获取的不对
            quartzLog.setExcuteTime(jec.getJobRunTime());
            getJobLogService().updateData(quartzLog);
        } catch (Exception e) {
            logger.error("记录job结束时间异常",e);
        }catch (Throwable e) {
            logger.error("记录job结束时间出错",e);
        }

    }
    private JobLogService getJobLogService(){
        return (JobLogService) SpringContextUtils.getBean("jobLogService");
    }
}
