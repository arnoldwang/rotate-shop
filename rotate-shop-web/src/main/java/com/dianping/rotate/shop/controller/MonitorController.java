package com.dianping.rotate.shop.controller;

import com.dianping.rotate.shop.model.ServiceResult;
import com.dianping.rotate.shop.service.TaskService;
import com.dianping.rotate.shop.utils.ConfigUtils;
import com.dianping.rotate.shop.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        result.setCode(ServiceResult.SERVICE_EXCEPTION);
        String msg = HttpClientUtil.get(getUrl(key, "trigger"));
        if(msg != null) {
            result.setCode(ServiceResult.SUCCESS);
            result.setMsg(msg);
        }
        return result;
    }

    @RequestMapping(value = "/disable", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResult disable(@RequestParam("key") String key){
        ServiceResult result = new ServiceResult();
        result.setCode(ServiceResult.SERVICE_EXCEPTION);
        String msg = HttpClientUtil.get(getUrl(key, "pause"));
        if(msg != null) {
            result.setCode(ServiceResult.SUCCESS);
            result.setMsg(msg);
        }
        return result;
    }

    @RequestMapping(value = "/enable", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResult enable(@RequestParam("key") String key){
        ServiceResult result = new ServiceResult();
        result.setCode(ServiceResult.SERVICE_EXCEPTION);
        String msg = HttpClientUtil.get(getUrl(key, "resume"));
        if(msg != null) {
            result.setCode(ServiceResult.SUCCESS);
            result.setMsg(msg);
        }
        return result;
    }

    private String getUrl(String key, String method) {
        String address = ConfigUtils.getIntegrationJobAddress();
        return "http://"+address+"/data/monitor/"+method+"?key=" + key;
    }

}
