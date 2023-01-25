package hjnu.wule.wetalk.controller;

//汉江师范学院 数计学院 吴乐创建于2022/12/21 22:18:15

import hjnu.wule.wetalk.domain.DoResult;
import hjnu.wule.wetalk.domain.User;
import hjnu.wule.wetalk.service.login.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin//解决前端跨域问题
public class LoginController
{
    static{
        System.out.println("LoginController Ready...");
    }
    @Autowired
    LoginService loginService;

    @RequestMapping("/login")
    public DoResult login(User user, HttpSession session)
    {
        System.out.println("1");
        String userId = user.getUserId();
        String password = user.getPassword();

        System.out.println(userId+password);
        DoResult doResult = new DoResult();

        if(loginService.login(userId,password))
        {
            doResult.setFlag(true);
            doResult.setMessage("登录成功");

            session.setAttribute("userId",userId);
        }else
        {
            doResult.setFlag(false);
            doResult.setMessage("登录失败");
        }
        System.out.println(doResult);

        return doResult;
    }

}
