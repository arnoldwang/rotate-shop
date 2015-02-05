package com.dianping.rotate.shop.controller;

import com.dianping.rotate.shop.model.ModelTest;
import com.dianping.rotate.shop.model.ServiceResult;
import com.dianping.rotate.shop.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by luoming on 15/2/3.
 */
@Controller
@RequestMapping(value = "/monitor")
public class MonitorController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResult refresh(@RequestParam("key") String key){
        ServiceResult result = new ServiceResult();
        result.setCode(ServiceResult.SUCCESS);
        result.setMsg(taskService.getTask(key));
        return result;
    }

    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResult reset(@RequestParam("key") String key){
        ServiceResult result = new ServiceResult();
        result.setCode(ServiceResult.SUCCESS);
        result.setMsg(taskService.resetTask(key));
        return result;
    }

    @RequestMapping(value = "/disable", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResult disable(@RequestParam("key") String key){
        ServiceResult result = new ServiceResult();
        result.setCode(ServiceResult.SUCCESS);
        result.setMsg(taskService.disableTask(key));
        return result;
    }

}
