package com.pbs.job.conf;


import com.pbs.job.custom.BizCustomScheduleTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JobManager {

    @Autowired
    private QuartzManager quartzManager;

    private Map<String,Object> map;

    public JobManager(){

    }
    public JobManager(Map<String,Object> map){
        this.map = map;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    /**
     * 添加任务
     * @param map
     */
    public void addJob(Map<String,Object> map){
        String id = map.get("ID").toString();
        String jobName = map.get("JOB_NAME").toString();
        String type = map.get("JOB_TYPE").toString();
        String jobImp = map.get("JOB_IMP").toString();
        String isRun = map.get("IS_RUN").toString();
        String cron = map.get("MIAO") +" "+ map.get("FEN")+" "+map.get("SHI")+" "
                + map.get("RI")+" "+map.get("YUE")+" "+map.get("XINGQI")+" "+map.get("NIAN");
        if("1".equals(isRun)){
            Map<String,String> jobData = new HashMap<>();
            jobData.put("JOB_IMP",jobImp);
            jobData.put("JOB_TYPE",type);
            jobData.put("JOB_NAME",id);
            jobData.put("JOB_DESCP",jobName);
            if("JAVA".equals(type)){
                try {
                    quartzManager.addJob(id,"pbsPeriodJobGroup",id,"pbsPeriodTriggerGroup",
                            Class.forName(jobImp),cron,jobData);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if("SQL".equals(type)){
                quartzManager.addJob(id,"pbsPeriodJobGroup",id,"pbsPeriodTriggerGroup",
                        BizCustomScheduleTask.class,cron,jobData);
            }
        }
    }

    /**
     * 修改任务
     * @param map
     */
    public void modifyJob(Map<String,Object> map) throws ClassNotFoundException {
        String id = map.get("ID").toString();
        String jobName = map.get("JOB_NAME").toString();
        String type = map.get("JOB_TYPE").toString();
        String jobImp = map.get("JOB_IMP").toString();
        String isRun = map.get("IS_RUN").toString();
        String cron = map.get("MIAO") +" "+ map.get("FEN")+" "+map.get("SHI")+" "
                + map.get("RI")+" "+map.get("YUE")+" "+map.get("XINGQI")+" "+map.get("NIAN");
        if("1".equals(isRun)){
            Map<String,String> jobData = new HashMap<>();
            jobData.put("JOB_IMP",jobImp);
            jobData.put("JOB_TYPE",type);
            jobData.put("JOB_NAME",id);
            jobData.put("JOB_DESCP",jobName);
            if("JAVA".equals(type)){
                quartzManager.modifyJob(id,"pbsPeriodJobGroup",id,"pbsPeriodTriggerGroup",
                        Class.forName(jobImp),cron,jobData);
            }
            if("SQL".equals(type)){
                quartzManager.modifyJob(id,"pbsPeriodJobGroup",id,"pbsPeriodTriggerGroup",
                        BizCustomScheduleTask.class,cron,jobData);
            }
        }
        if("0".equals(isRun)){
            quartzManager.pauseJob(id,"pbsPeriodJobGroup");
        }
    }

    /**
     * 删除任务
     * @param map
     */
    public void removeJob(Map<String,Object> map){
        String id = map.get("ID").toString();
        String type = map.get("JOB_TYPE").toString();
        quartzManager.removeJob(id,"pbsPeriodJobGroup",id,"pbsPeriodTriggerGroup");
    }
    /**
     * 启动单个任务
     * @param map
     */
    public void startOneJob(Map<String,Object> map){
        String id = map.get("ID").toString();
        //String jobName = map.get("JOB_NAME").toString();
        String type = map.get("JOB_TYPE").toString();
        boolean isRun = (boolean)map.get("IS_RUN");
        if(isRun){
            quartzManager.startOneJob(id,"pbsPeriodJobGroup",id,"pbsPeriodTriggerGroup");
        }
    }
    /**
     * 启动所有任务
     */
    public void startJobs(){
        quartzManager.startJobs();
    }
    /**
     * 停止所有任务
     */
    public void shutdownJobs(){
        quartzManager.shutdownJobs();
    }
}
