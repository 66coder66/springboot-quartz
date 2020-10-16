package com.pbs.job.custom;

import com.pbs.job.custom.mapper.BizCustomScheduleTaskMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;



@Service
public class BizCustomScheduleTaskService {

    private static final Logger logger = LoggerFactory.getLogger(BizCustomScheduleTaskService.class);

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    @Autowired
    BizCustomScheduleTaskMapper BizCustomScheduleTaskMapper;

    /**
     * 无事务
     * @param sql
     * @throws Exception
     */
    public void executeWithoutTransactional(String sql) {
        if(!StringUtils.isEmpty(sql)){
            logger.info("----------定时任务执行SQL-----------------"+sql);
            BizCustomScheduleTaskMapper.executeSql(sql);

        }
    }

    /**
     * 开启事务
     * @param sql
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void executeWithTransactional(String sql)throws Exception {
        if(!StringUtils.isEmpty(sql)){
            logger.info("----------定时任务执行SQL(事务)-----------------"+sql);
            BizCustomScheduleTaskMapper.executeSql(sql);
        }
    }
}
