package com.dianping.rotate.shop.controller;

import com.dianping.rotate.shop.model.ModelTest;
import com.dianping.rotate.shop.model.ServiceResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zaza on 15/1/29.
 */
@Controller
@RequestMapping(value = "/home")
public class WelcomeController {
    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    public String welcome(Model model){
        return "index";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResult testForParam(HttpServletRequest request,
                              @RequestParam(value = "keyword", defaultValue = "") String keyword){
        ServiceResult result = new ServiceResult();
        result.setCode(ServiceResult.SUCCESS);
        result.setMsg(new Object());
        return result;
    }

    @RequestMapping(value = "/{testId}/test", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResult testForPathVar(HttpServletRequest request,
                                        @PathVariable("testId") int shopId){
        ServiceResult result = new ServiceResult();
        result.setCode(ServiceResult.SUCCESS);
        result.setMsg(new Object());
        return result;
    }

    @RequestMapping(value = "/{testId}/test", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResult testForPost(HttpServletRequest request,
                                     @RequestBody ModelTest modelTest,
                                     @PathVariable("testId") int testId){
        ServiceResult result = new ServiceResult();
        result.setCode(ServiceResult.SUCCESS);
        result.setMsg(new Object());
        return result;

    }
}
