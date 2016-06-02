package cn.dd.core.front.rest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by wishflyer on 2016/5/31.
 */
@Controller
public class ExceptionHandlingController {

    @RequestMapping("/404")
    public String error_404(){
        //System.out.println("404(),,,,");
        //TODO 这里不知道为什么一直在访问，待处理
        return "error/404";
    }

    @RequestMapping("/500")
    public String error_500(){
        System.out.println("500()...");
        return "error/500";
    }

}
