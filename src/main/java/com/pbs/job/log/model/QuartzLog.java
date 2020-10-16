package com.pbs.job.log.model;

import java.io.Serializable;
import java.util.Date;

public class QuartzLog implements Serializable {

    private long id;
    private Date createDate;//创建时间
    private String jobId;//pbs_qrtz_job表ID
    private String jobName;//pbs_qrtz_job表JOB_NAME
    private String jobGroup;//任务组名
    private String methodName;//任务方法
    private String methodParams;//任务参数
    private long excuteTime;//任务执行次数
    private String jobMsg;//日志信息
    private String jobStatus;//执行状态
    private String excepMsg;//异常信息

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodParams() {
        return methodParams;
    }

    public void setMethodParams(String methodParams) {
        this.methodParams = methodParams;
    }

    public long getExcuteTime() {
        return excuteTime;
    }

    public void setExcuteTime(long excuteTime) {
        this.excuteTime = excuteTime;
    }

    public String getJobMsg() {
        return jobMsg;
    }

    public void setJobMsg(String jobMsg) {
        this.jobMsg = jobMsg;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getExcepMsg() {
        return excepMsg;
    }

    public void setExcepMsg(String excepMsg) {
        this.excepMsg = excepMsg;
    }

    public String toString() {
        return new StringBuilder()
                .append("Id:"+getId())
                .append(",jobId"+getJobId())
                .append(",jobName"+getJobName())
                .append(",jobGroup"+getJobGroup())
                .append(",methodName"+getMethodName())
                .append(",methodParams"+getMethodParams())
                .append(",excuteTime"+getExcuteTime())
                .append(",jobMsg"+getJobMsg())
                .append(",jobStatus"+getJobStatus())
                .append(",exceptionMsg"+getExcepMsg())
                .append(",createDate"+getCreateDate())
                .toString();
    }
}
