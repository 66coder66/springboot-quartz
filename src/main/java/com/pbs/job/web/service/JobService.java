package com.pbs.job.web.service;

import com.pbs.job.utils.SequenceUtils;
import com.pbs.job.web.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class JobService {
    @Autowired
    JobMapper jobMapper;
    @Autowired
    SequenceUtils seq;
    /**
     * 查询列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectJobList(Map<String, Object> param) {
        return jobMapper.selectJobList(param);
    }
    /**
     * 查询单条数据
     * @param param
     * @return
     */
    public Map<String, Object> getData(Map<String, Object> param) {
        return jobMapper.getData(param);
    }

    /**
     * 保存insert
     * @param map
     * @return
     */
    public int save(Map<String, Object> map) {
        String id = seq.getUUID();
        map.put("ID",id);
        LocalDateTime now = LocalDateTime.now();
        map.put("CREATE_DATE",now);
        map.put("UPDATE_DATE",now);
        map.put("MIAO","0");
        //map.put("UPDATE_USER",map.get("CREATE_USER"));
        int i = jobMapper.insertData(map);
        return i;
    }

    public int update(Map<String, Object> map) {
        LocalDateTime now = LocalDateTime.now();
        map.put("UPDATE_DATE",now);
        map.put("MIAO","0");
        int i = jobMapper.updateData(map);
        return i;
    }

    public int delDate(Map<String, Object> map) {
        LocalDateTime now = LocalDateTime.now();
        map.put("UPDATE_DATE",now);
        int i = jobMapper.delDate(map);
        return i;
    }
}
