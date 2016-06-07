package cn.dd.famory.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wishflyer on 2016/6/7.
 */
@Controller
public class RouterMappingHandler {


    @RequestMapping("/index")
    public String index(){
        return "main/index";
    }


    @RequestMapping("/fashion")
    public String gotoFashionPage(){
        return "main/fashion";
    }


    @RequestMapping("/content")
    public String gotoContentPage(){
        return "main/content";
    }

    @RequestMapping("/about")
    public String gotoAboutPage(){
        return "main/about";
    }

}
