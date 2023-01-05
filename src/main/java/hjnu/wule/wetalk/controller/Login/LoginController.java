package hjnu.wule.wetalk.controller.Login;

//汉江师范学院 数计学院 吴乐创建于2022/12/21 22:18:15

import hjnu.wule.wetalk.domain.DoResult;
import hjnu.wule.wetalk.domain.User;
import hjnu.wule.wetalk.service.login.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin//解决前端跨域问题
public class LoginController
{
    @Autowired
    LoginService loginService;

    @RequestMapping("/login")
    @ResponseBody
    public DoResult login(@RequestBody User user, HttpSession session)
    {
        String userId = user.getUserId();
        String password = user.getPassword();
        DoResult doResult = new DoResult();

        if(loginService.login(userId,password))
        {
            doResult.setFlag(true);
            doResult.setMessage("登录成功");

            session.setAttribute("user",userId);
        }else
        {
            doResult.setFlag(false);
            doResult.setMessage("登录失败");
        }

        return doResult;
    }

}
