package hjnu.wule.wetalk.controller;

//汉江师范学院 数计学院 吴乐创建于2023/1/24 23:21:26

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GetView
{
    static {
        System.out.println("GetView Ready...");
    }
    /**
     进入聊天室页面
     * */
    @RequestMapping("/getView")
    public String getView()
    {
        System.out.println("GetView.getView start running");
        return "wsTestRoom";
    }

}
