package com.pbs.job.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexViewController {

    /**
     * 欢迎页
     * @param map
     * @return
     */
    @RequestMapping("/index")
    public String index(ModelMap map) {

        return "index";
    }

}
