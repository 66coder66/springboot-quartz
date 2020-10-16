package com.pbs.job.test.mapper;

import java.util.Map;

public interface TaskMapper {

    Map<String, Object> selectUser(Map<String, Object> param);

    int insertRole(Map<String, Object> param);

    int deleteRole(Map<String, Object> param);
}
