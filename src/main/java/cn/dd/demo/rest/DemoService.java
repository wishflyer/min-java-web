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

    @RequestMapping("/index")
    public String index(){
        System.out.println("index()...");
        return "index";
    }


    @RequestMapping("/header_v3")
    public String header_v3(){
        System.out.println("header_v3()...");
        return "header/header_v3";
    }


    @RequestMapping("/index2")
    public String index2(){
        System.out.println("index2()...");
        return "index2";
    }




    @RequestMapping("/test")
    @ResponseBody
    public  String test(){
        System.out.println("test()...");
        return "test";
    }
}