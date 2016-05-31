package cn.dd.core.i18n;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by wishflyer on 2016/5/31.
 */
@Controller
public class langController {

    @RequestMapping("/i18n")
    //@ResponseBody
    public String lang(HttpServletRequest request, HttpServletResponse response) {
        String langType = request.getParameter("lang");
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        //设置3个月内有效
        cookieLocaleResolver.setCookieMaxAge(8000000);
        //如果这里设置了名称的话，xml配置文件里也要对应设置名称，不然会找不到
        //ookieLocaleResolver.setCookieName("language");
        if (langType.equals("zh")) {
            Locale locale = new Locale("zh", "CN");
            //request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
            cookieLocaleResolver.setLocale(request, response, locale);
        } else if (langType.equals("en")) {
            Locale locale = new Locale("en", "US");
            //request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
            cookieLocaleResolver.setLocale(request, response, locale);
        } else {
            cookieLocaleResolver.setLocale(request, response, LocaleContextHolder.getLocale());
            //request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, LocaleContextHolder.getLocale());
        }
        //返回主页
        return "redirect:/";
    }
}
