package com.pbs.job.web.action;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pbs.exception.ResultBody;
import com.pbs.job.conf.JobManager;
import com.pbs.job.utils.IPUtils;
import com.pbs.job.web.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/job")
public class JobController {

    @Autowired
    JobService jobService;
    @Resource
    JobManager jobManager;
    /**
     * 列表页
     * @param map
     * @return
     */
    @RequestMapping("/list")
    public String list(ModelMap map) {

        return "list";
    }

    /**
     * 新增页
     * @param map
     * @return
     */
    @RequestMapping("/create")
    public String create(ModelMap map) {

        return "create";
    }
    /**
     * 编辑页
     * @param map
     * @return
     */
    @RequestMapping("/edit")
    public String edit(ModelMap map,String id,String type) {
        //TODO
        if(!StringUtils.isEmpty(id)){
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("id",id);
            param.put("isDel", 0);
            Map<String, Object> job = jobService.getData(param);
            map.put("job",job);
            String jobImp = job.get("JOB_IMP").toString();
            jobImp = jobImp.replaceAll("'","&#39;");
            job.put("JOB_IMP",jobImp);
            map.put("jobJson",JSON.toJSONString(job));
        }
        map.put("viewType",type);
        return "edit";
    }

    /**
     * 列表数据
     * @return
     */
    @RequestMapping("/getListJson")
    public @ResponseBody String getListJson(String jobName,int page, int limit) {
        //TODO
        Map<String, Object> json = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("jobName", jobName);
        Page<Object> pageHelper = new Page<>(page, limit);
        param.put("page", pageHelper);
        List<Map<String,Object>> list = jobService.selectJobList(param);
        json.put("code", 0);
        json.put("msg", "success");
        json.put("count", pageHelper.getTotal());
        json.put("data", list);
        return JSON.toJSONString(json);
    }
    /**
     * 单个数据
     * @param id
     * @return
     */
    @RequestMapping("/getDataJson")
    public @ResponseBody String getDataJson(String id) {
        Map<String, Object> json = new HashMap<String, Object>();
        if(StringUtils.isEmpty(id)){
            json.put("code", -1);
            json.put("msg", "参数id为空");
            return JSON.toJSONString(json);
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        param.put("isDel", 0);
        Map<String,Object> data = jobService.getData(param);
        json.put("code", 0);
        json.put("msg", "success");
        json.put("data", data);
        return JSON.toJSONString(json);
    }
    /**
     * 保存数据
     * @param map
     * @return
     */
    @RequestMapping("/save")
    public @ResponseBody ResultBody save(HttpServletRequest request, @RequestBody Map<String,Object> map) {
        ResultBody resultBody = new ResultBody();
        if(map !=null && map.size()>0){
            //保存数据
            String ip = IPUtils.getIpAddress(request);
            map.put("CREATE_USER",ip);
            int i = jobService.save(map);
            //TODO
            //启动定时器
            if(i>0){
                jobManager.addJob(map);
            }
            resultBody.setCode("1");
            resultBody.setMessage("保存对象成功");
        }else{
            resultBody.setCode("-1");
            resultBody.setMessage("保存对象为空");
        }
        return resultBody;
    }
    /**
     * 修改数据
     * @param map
     * @return
     */
    @RequestMapping("/update")
    public @ResponseBody ResultBody update(HttpServletRequest request,@RequestBody Map<String,Object> map) throws ClassNotFoundException {
        //TODO
        ResultBody resultBody = new ResultBody();
        if(map !=null && map.size()>0){
            //保存数据
            String ip = IPUtils.getIpAddress(request);
            map.put("UPDATE_USER",ip);
            int i = jobService.update(map);
            //更新定时器
            if(i>0){
                jobManager.modifyJob(map);
            }
            resultBody.setCode("1");
            resultBody.setMessage("保存对象成功");
        }else{
            resultBody.setCode("-1");
            resultBody.setMessage("保存对象为空");
        }
        return resultBody;
    }
    /**
     * 标记删除可以多条：以“,”分割
     * @param ids
     * @return
     */
    @RequestMapping("/delDate")
    public @ResponseBody ResultBody delDates(HttpServletRequest request,String[] ids) {
        ResultBody resultBody = new ResultBody();
        if(ids.length>0){
            //删数据
            Map<String, Object> map = new HashMap<String, Object>();
            String ip = IPUtils.getIpAddress(request);
            map.put("UPDATE_USER",ip);
            map.put("idlist", Arrays.asList(ids));
            int i = jobService.delDate(map);
            if(i>0){
                //TODO
                Map<String, Object> param = new HashMap<String, Object>();
                for(String id :ids){
                    param.put("id", id);
                    param.put("isDel", 1);
                    Map<String,Object> data = jobService.getData(param);
                    //删除定时器trigger
                    jobManager.removeJob(data);
                    param.clear();
                }
            }
        }
        resultBody.setCode("1");
        resultBody.setMessage("删除成功");
        return resultBody;
    }
}
