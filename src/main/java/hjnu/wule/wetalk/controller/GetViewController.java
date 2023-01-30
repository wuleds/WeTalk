package hjnu.wule.wetalk.controller;

//汉江师范学院 数计学院 吴乐创建于2022/12/28 23:21:26

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/getView")
public class GetViewController
{
    static {
        System.out.println("GetView Ready...");
    }

    /**根据网页的名跳转页面
     * @return ModelAndView*/
    @RequestMapping("/{viewName}")
    public ModelAndView getView(@PathVariable String viewName)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(viewName);

        return modelAndView;
    }

}
