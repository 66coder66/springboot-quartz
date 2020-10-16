package com.pbs.job.log.mapper;

import com.pbs.job.log.model.QuartzLog;

import java.util.List;
import java.util.Map;

public interface JobLogMapper {

    List<Map<String, Object>> selectJobLogList(Map<String, Object> param);

    Map<String, Object> getData(Map<String, Object> param);

    int insertData(QuartzLog quartzLog);

    int updateDate(QuartzLog quartzLog);
}
