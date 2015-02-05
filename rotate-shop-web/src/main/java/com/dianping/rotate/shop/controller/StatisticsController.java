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

    @RequestMapping(value = "/message/fail", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResult getProcessFailMessage(@RequestParam(value = "source", defaultValue = "0") int source,
                                               @RequestParam(value = "type", defaultValue = "0") int type,
                                               @RequestParam(value = "limit", defaultValue = "20") int limit,
                                               @RequestParam(value = "offset", defaultValue = "0") int offset){
        StatisticsModel statisticsModel = statisticsService.getProcessMessage(source,type, MessageStatus.FAIL,limit,offset);
        ServiceResult result = new ServiceResult();
        result.setCode(ServiceResult.SUCCESS);
        result.setMsg(statisticsModel);
        return result;
    }

    @RequestMapping(value = "/message/success", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResult getProcessSuccessMessage(@RequestParam(value = "source", defaultValue = "0") int source,
                                                  @RequestParam(value = "type", defaultValue = "0") int type,
                                                  @RequestParam(value = "limit", defaultValue = "20") int limit,
                                                  @RequestParam(value = "offset", defaultValue = "0") int offset){
        StatisticsModel statisticsModel = statisticsService.getProcessMessage(source,type, MessageStatus.SUCCESS,limit,offset);
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

}
