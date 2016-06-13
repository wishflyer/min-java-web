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


    @RequestMapping("/index2")
    public String index2(){
        return "main/index2";
    }

    @RequestMapping("/index3")
    public String index3(){
        return "main/index3";
    }


    @RequestMapping("/index4")
    public String index4(){
        return "main/index4";
    }


    @RequestMapping("/fashion")
    public String gotoFashionPage(){
        return "main/fashion";
    }


    @RequestMapping("/content")
    public String gotoContentPage(){
        return "main/content";
    }


    @RequestMapping("/portfolio")
    public String gotoPortfolioPage(){
        return "main/portfolio";
    }


    @RequestMapping("/portfolio2")
    public String gotoPortfolioPage2(){
        return "main/portfolio2";
    }


    @RequestMapping("/style")
    public String gotoStylePage(){
        return "main/style";
    }


    @RequestMapping("/match")
    public String gotoMatchPage(){
        return "main/match";
    }


    @RequestMapping("/map")
    public String gotoMapPage(){
        return "main/map";
    }


    @RequestMapping("/search")
    public String gotoSearchPage(){
        return "main/search";
    }


    @RequestMapping("/about")
    public String gotoAboutPage(){
        return "main/about";
    }

}
