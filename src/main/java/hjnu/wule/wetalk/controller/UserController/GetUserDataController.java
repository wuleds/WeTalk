package hjnu.wule.wetalk.controller.UserController;

//汉江师范学院 数计学院 吴乐创建于2023/1/5 23:12:45

import hjnu.wule.wetalk.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("/getUserData")
public class GetUserDataController
{
    @Autowired
    UserService userService;

    @RequestMapping("/getUserNameById")
    @ResponseBody
    public String getUserId(HttpSession httpSession)
    {
        System.out.println("1");
        String userId = (String) httpSession.getAttribute("userId");
        String userName = userService.getUserNameById(userId);
        System.out.println(userId);
        System.out.println(userName);
        return userName;
    }

}
