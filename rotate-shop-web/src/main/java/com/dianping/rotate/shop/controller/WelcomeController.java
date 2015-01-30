package com.dianping.rotate.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zaza on 15/1/29.
 */
@Controller
public class WelcomeController {
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String welcome(Model model){
        return "index";
    }
}
