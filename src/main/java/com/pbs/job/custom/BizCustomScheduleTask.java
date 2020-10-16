package com.pbs.job.custom;

import com.pbs.job.utils.RegexUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@DisallowConcurrentExecution
public class BizCustomScheduleTask implements Job {

    private static final Logger logger = LoggerFactory.getLogger(BizCustomScheduleTask.class);

    @Autowired
    BizCustomScheduleTaskService bizCustomScheduleTaskService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        if(!jobDataMap.isEmpty() && jobDataMap.size()>0){
            String type = jobDataMap.get("JOB_TYPE").toString();
            try {
                if("SQL".equals(type)){
                    StringBuffer sqlSb = new StringBuffer();
                    String jobImp = jobDataMap.get("JOB_IMP").toString();
                    jobImp = jobImp.replaceAll("\r|\n","");
                    String transRegex = "(?<=<transaction>)[^<>].+?(?=</transaction>)";//事务
                    String sqlRegex = "(?<=<sql>)[^<>].+?(?=</sql>)";//sql
                    boolean transBool = false;
                    RegexUtils regexUtil = new RegexUtils();
                    String transStr = regexUtil.getSubUtilSimple(jobImp,transRegex);
                    String sql = regexUtil.getSubUtilSimple(jobImp,sqlRegex);
                    logger.info("**************BizCustomScheduleTask执行定时任务="+jobDataMap.get("JOB_NAME")+",*************sql="+sql);
                    if(!StringUtils.isEmpty(transStr)){
                        transBool = Boolean.parseBoolean(transStr);
                    }
                    if(transBool){
                        bizCustomScheduleTaskService.executeWithTransactional(sql);
                    }else{
                        bizCustomScheduleTaskService.executeWithoutTransactional(sql);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
