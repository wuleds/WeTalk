package hjnu.wule.wetalk.controller;
//汉江师范学院 数计学院 吴乐创建于2023/1/29 15:46:55

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**访问网站初始页面*/
@Controller
public class StartingController
{
    static {
        System.out.println("StartingController Ready...");
    }

    @RequestMapping("/")
    public ModelAndView start()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Login");

        return modelAndView;
    }

}
