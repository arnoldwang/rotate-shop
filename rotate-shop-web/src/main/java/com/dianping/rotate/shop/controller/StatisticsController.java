package com.dianping.rotate.shop.controller;

import com.dianping.rotate.shop.constants.MessageStatus;
import com.dianping.rotate.shop.model.ServiceResult;
import com.dianping.rotate.shop.model.StatisticsModel;
import com.dianping.rotate.shop.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zaza on 15/2/4.
 */
@Controller
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(value = "/message/detail", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResult getProcessMessage(@RequestParam(value = "source", defaultValue = "0") int source,
                                           @RequestParam(value = "type", defaultValue = "0") int type,
                                           @RequestParam(value = "status", defaultValue = "0") int status,
                                           @RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
                                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        StatisticsModel statisticsModel = statisticsService.getProcessMessage(source,type, status,pageSize,pageSize*(pageIndex-1));
        ServiceResult result = new ServiceResult();
        result.setCode(ServiceResult.SUCCESS);
        result.setMsg(statisticsModel);
        return result;
    }

    @RequestMapping(value = "/message/delete", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResult deleteMessage(@RequestParam(value = "id", defaultValue = "0") int id){
        statisticsService.deleteMessage(id);
        ServiceResult result = new ServiceResult();
        result.setCode(ServiceResult.SUCCESS);
        result.setMsg(new Object());
        return result;
    }

    @RequestMapping(value = "/message/retry", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResult retryMessage(@RequestParam(value = "id", defaultValue = "0") int id){
        ServiceResult result = new ServiceResult();
        StatisticsModel statisticsModel = statisticsService.retryMessage(id);
        result.setCode(ServiceResult.SUCCESS);
        result.setMsg(statisticsModel);
        return result;
    }

    @RequestMapping(value = "/message/retry/all", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResult retryAllMessage(@RequestParam(value = "type", defaultValue = "0") int type,
                                         @RequestParam(value = "source", defaultValue = "0") int source){
        ServiceResult result = new ServiceResult();
        StatisticsModel statisticsModel = statisticsService.retryAllMessage(type,source);
        result.setCode(ServiceResult.SUCCESS);
        result.setMsg(statisticsModel);
        return result;
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResult getMessageStatistics(@RequestParam(value = "type", defaultValue = "0") int type,
                                              @RequestParam(value = "source", defaultValue = "0") int source){
        StatisticsModel statisticsModel = statisticsService.getMessageStatistics(type,source);
        ServiceResult result = new ServiceResult();
        result.setCode(ServiceResult.SUCCESS);
        result.setMsg(statisticsModel);
        return result;
    }

    @RequestMapping(value = "/message/all", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResult getAllMessageStatistics(){
        StatisticsModel statisticsModel = statisticsService.getAllMessageStatistics();
        ServiceResult result = new ServiceResult();
        result.setCode(ServiceResult.SUCCESS);
        result.setMsg(statisticsModel);
        return result;
    }

    @RequestMapping(value = "/message/search", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResult getMessageByID(@RequestParam(value = "id", defaultValue = "0") int id){
        StatisticsModel statisticsModel = statisticsService.getMessageByID(id);
        ServiceResult result = new ServiceResult();
        result.setCode(ServiceResult.SUCCESS);
        result.setMsg(statisticsModel);
        return result;
    }

}
