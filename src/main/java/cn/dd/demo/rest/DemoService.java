package cn.dd.demo.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wishflyer on 2016/5/18.
 */
@Controller
public class DemoService {

    @RequestMapping("/hello")
    public ModelAndView hello(){
        System.out.println("hello()...");
        ModelAndView modelAndView = new ModelAndView("hello");
        return modelAndView;
    }


    @RequestMapping("/header_v3")
    public String header_v3(){
        System.out.println("header_v3()...");
        return "header/header_v3";
    }


    @RequestMapping("/test")
    @ResponseBody
    public  String test(){
        System.out.println("test()...");
        return "test";
    }
}