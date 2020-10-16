package com.pbs.job.test.service;

import com.pbs.job.test.mapper.TaskMapper;
import com.pbs.job.utils.SequenceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class TestTaskService {

    @Resource
    TaskMapper taskMapper;
    @Resource
    SequenceUtils seq;

    @Transactional(rollbackFor = Exception.class)
    public int save() throws Exception{
        int i = 0;
        Map<String,Object> param = new HashMap<>();
        for(int j = 0;j<2;j++){
            param.clear();
            String id = seq.getUUID();
            param.put("ID",id);
            param.put("ROLE_GROUP","papapap"+j);
            param.put("ROLE_NAME","啪啪啪"+j);
            param.put("ORDER_INDEX",55+j);
            param.put("ORG_ID","PBS_ORG_COMPANY_ID_002");
            i+= taskMapper.insertRole(param);
            //Thread.sleep(6000);
        }
        return i;
    }

}
