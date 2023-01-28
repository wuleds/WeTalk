package hjnu.wule.wetalk.controller;

//汉江师范学院 数计学院 吴乐创建于2022/12/28 14:25:55

import hjnu.wule.wetalk.domain.User;
import hjnu.wule.wetalk.domain.UserLogin;
import hjnu.wule.wetalk.domain.UserSignup;
import hjnu.wule.wetalk.service.LoginService;
import hjnu.wule.wetalk.service.SignupService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
@CrossOrigin//解决前端跨域问题
@RequestMapping("/user")
public class UserController
{
    static
    {
        System.out.println("UserController Ready...");
    }

    @Autowired
    LoginService loginService;

    /**登录
     * @return ModelAndView*/
    @PostMapping("/login")
    public ModelAndView login(UserLogin userLogin, HttpSession httpSession)
    {
        System.out.println("UserController.login start running");

        String userId = userLogin.getUserId();
        String password = userLogin.getPassword();

        ModelAndView modelAndView = new ModelAndView();

        System.out.println(userLogin);

        if (Objects.equals(userId, "") || Objects.equals(password, ""))
        {
            modelAndView.addObject("flag", 0);
            modelAndView.addObject("message", "未输入账号或密码");
            modelAndView.setViewName("LoginError");
            System.out.println("未输入账号或密码");
        } else
        {
            User user;
            //查询是否可以登录
            user = loginService.login(userId, password);

            if (user != null)
            {
                String userName = user.getUserId();
                modelAndView.addObject("flag", 1);
                modelAndView.addObject("message", "登录成功");
                modelAndView.addObject("userId", userId);
                modelAndView.addObject("userName", userName);

                modelAndView.setViewName("testWebsocket");

                System.out.println(user.getUserName()+"登录成功");

                //向websocket传递用户信息
                httpSession.setAttribute("userId", userId);
                httpSession.setAttribute("userName", userName);
            } else
            {
                modelAndView.addObject("flag", 0);
                modelAndView.addObject("message", "用户名或密码错误、或该用户不存在");
                modelAndView.setViewName("LoginError");
                System.out.println("用户名或密码错误、或该用户不存在");
            }
        }
        System.out.println("UserController.login end run");

        return modelAndView;
    }

    @Autowired
    SignupService signupService;

    /**注册
     * @return ModelAndView*/
    @PostMapping("/signup")
    public ModelAndView signup(UserSignup userSignup)
    {
        System.out.println("UserController.signup start running");

        System.out.println(userSignup);
        ModelAndView modelAndView = new ModelAndView();
        String userName = userSignup.getUserName();
        String userId = userSignup.getUserId();
        String password = userSignup.getPassword();

        //如果输入有空值
        if (Objects.equals(userName, "") || Objects.equals(userId, "") || Objects.equals(password, ""))
        {
            modelAndView.setViewName("SignupError");
            modelAndView.addObject("message", "账号、名字或密码未输入");
            System.out.println("账号、名字或密码未输入");
        } else
        {
            //如果账号密码位数不够6位
            if (userId.length() < 6 || password.length() < 6)
            {
                modelAndView.setViewName("SignupError");
                modelAndView.addObject("message", "账号或密码的位数必须大于6位");
                System.out.println("账号或密码的位数必须大于6位");
            } else
            {
                //满足条件，进行注册
                boolean a = signupService.signupUserData(userName, userId, password);
                if (a)
                {
                    //注册成功
                    modelAndView.setViewName("Login");
                    System.out.println(userName + "注册成功");
                } else
                {
                    //注册出错
                    modelAndView.setViewName("SignupError");
                    modelAndView.addObject("message", "账号已经存在");
                }
            }

        }
        System.out.println("UserController.signup end run");

        return modelAndView;
    }
}
