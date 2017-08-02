package com.assetman.fiaame.controllers;

import com.assetman.fiaame.models.User;
import io.ebean.EbeanServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by hengfeihu on 2017/8/2.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private EbeanServer ebeanServer;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    String userlist(Model model) {
        List<User> users = ebeanServer.find(User.class).where().findList();
        model.addAttribute("users", users);
        return "/user/userlist";
    }
}
