package com.pbs.job.log.service;

import com.alibaba.fastjson.JSONObject;
import com.pbs.job.log.mapper.JobLogMapper;
import com.pbs.job.log.model.QuartzLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("jobLogService")
public class JobLogService {

    @Autowired
    JobLogMapper jobLogMapper;

    public List<Map<String, Object>> selectJobLogList(Map<String, Object> param){
        return jobLogMapper.selectJobLogList(param);
    }
    public Map<String, Object> getData(Map<String, Object> param){
        return jobLogMapper.getData(param);
    }
    public QuartzLog insertData(Map<String, Object> param){
        //TODO
        String json = JSONObject.toJSONString(param);
        QuartzLog quartzLog = JSONObject.parseObject(json, QuartzLog.class);
        long id = jobLogMapper.insertData(quartzLog);
        return quartzLog;
    }

    public int updateData(QuartzLog quartzLog) {
        return jobLogMapper.updateDate(quartzLog);
    }
}
