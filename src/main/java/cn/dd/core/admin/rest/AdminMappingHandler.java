package cn.dd.core.admin.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wishflyer on 2016/5/18.
 */
@Controller
public class AdminMappingHandler {

    @RequestMapping("/admin")
    public String index(){
        return "admin";
    }

}