package hjnu.wule.wetalk.controller;

//汉江师范学院 数计学院 吴乐创建于2023/1/24 23:21:26

import com.alibaba.fastjson.JSON;
import hjnu.wule.wetalk.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GetView
{
    static {
        System.out.println("GetView Ready...");
    }
    /**
     跳转页面
     * */
    @RequestMapping("/getView/{viewName}")
    public ModelAndView getView(@PathVariable String viewName)
    {
        System.out.println("GetView.getView start running");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(viewName);

        System.out.println("GetView.getView Go "+viewName);
        System.out.println("GetView.getView end run");

        return modelAndView;
    }

}
