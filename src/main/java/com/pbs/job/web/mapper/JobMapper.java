package com.pbs.job.web.mapper;

import java.util.List;
import java.util.Map;

public interface JobMapper {

    List<Map<String, Object>> selectJobList(Map<String, Object> param);

    Map<String, Object> getData(Map<String, Object> param);

    int insertData(Map<String, Object> map);

    int updateData(Map<String, Object> map);

    int delDate(Map<String, Object> map);
}
