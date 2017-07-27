package com.assetman.fiaame.controllers;

import com.assetman.fiaame.configuration.MvcConfiguration;
import com.assetman.fiaame.models.Student;
import com.assetman.fiaame.models.Test;
import com.assetman.fiaame.models.User;
import io.ebean.EbeanServer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by hengfeihu on 2017/7/24.
 */
@Controller
public class HelloController {
    private Logger log = Logger.getLogger(HelloController.class);
    @Autowired
    private EbeanServer ebeanServer;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    String index(Model model) {
        model.addAttribute("key", "hello world!----------------index");
        return "/index";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    String login(Model model, @Valid @ModelAttribute(value = "user") User user, HttpSession session) {
        System.out.println("登陆===================" + user.getUsername());
        User user1 = ebeanServer.find(User.class).where().eq("username", user.getUsername()).eq("password", user.getPassword()).findUnique();
        if (user1 != null) {
            session.setAttribute(MvcConfiguration.SESSION_KEY, user);
            model.addAttribute("username", user1.getUsername());
            return "/login";
        }
        model.addAttribute("key", "登陆失败----------------index");
        return "redirect:/index";
    }

    @RequestMapping("list")
    String list(Model model) {
        List<Student> students = ebeanServer.find(Student.class).findList();
        model.addAttribute("students", students);
        return "/list";
    }

    @RequestMapping(value = "{id}")
    Student findbyid(@PathVariable Long id) {
        return ebeanServer.find(Student.class).where().eq("id", id).findUnique();
    }

    @RequestMapping("test/add")
    public List<Test> addTest() {
        Test test = new Test();
        test.test = 2;
        ebeanServer.save(test);
        log.info("======================================添加Test========================================");
        return ebeanServer.find(Test.class).findList();
    }
}
