package com.pbs.job.custom.mapper;

import org.apache.ibatis.annotations.Param;

public interface BizCustomScheduleTaskMapper {
    void executeSql(@Param("sql")String sql);
}
