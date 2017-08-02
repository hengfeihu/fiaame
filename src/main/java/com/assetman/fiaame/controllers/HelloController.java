package com.assetman.fiaame.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hengfeihu on 2017/7/24.
 */
@Controller
public class HelloController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    String index() {
        return "/index";
    }
}
