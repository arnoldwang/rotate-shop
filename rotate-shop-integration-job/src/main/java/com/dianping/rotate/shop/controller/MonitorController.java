package com.dianping.rotate.shop.controller;

import com.dianping.rotate.shop.service.TaskOperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by luoming on 15/2/11.
 */
@Controller
@RequestMapping(value = "/monitor")
public class MonitorController {

    @Autowired
    private TaskOperService taskOperService;

    @RequestMapping(value = "/trigger", method = RequestMethod.GET)
    @ResponseBody
    public boolean trigger(@RequestParam("key") String key){
        return taskOperService.trigger(key);
    }

    @RequestMapping(value = "/pause", method = RequestMethod.GET)
    @ResponseBody
    public boolean pause(@RequestParam("key") String key){
        return taskOperService.pause(key);
    }

    @RequestMapping(value = "/resume", method = RequestMethod.GET)
    @ResponseBody
    public boolean resume(@RequestParam("key") String key){
        return taskOperService.resume(key);
    }

}
