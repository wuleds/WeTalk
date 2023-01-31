package hjnu.wule.wetalk.controller;

//汉江师范学院 数计学院 吴乐创建于2022/12/28 14:25:55

import hjnu.wule.wetalk.domain.User;
import hjnu.wule.wetalk.domain.UserLogin;
import hjnu.wule.wetalk.domain.UserSignup;
import hjnu.wule.wetalk.service.LoginService;
import hjnu.wule.wetalk.service.SignupService;
import hjnu.wule.wetalk.service.UserService;
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
    static {
        System.out.println("UserController Ready...");
    }

    @Autowired
    LoginService loginService;

    /**登录
     * @return ModelAndView*/
    @PostMapping("/login")
    public ModelAndView login(UserLogin userLogin, HttpSession httpSession)
    {
        String userId = userLogin.getUserId();
        String password = userLogin.getPassword();

        ModelAndView modelAndView = new ModelAndView();

        if (Objects.equals(userId, "") || Objects.equals(password, ""))
        {
            modelAndView.addObject("flag", 0);
            modelAndView.addObject("message", "未输入账号或密码");
            modelAndView.setViewName("LoginError");
        } else
        {
            User user;
            //查询是否可以登录
            user = loginService.login(userId, password);

            if (user != null)
            {
                //如果没有此账号当前不在线
                if(WebSocketServer.getUserIdAndHttpSessionId().get(userId) == null)
                {
                    String userName = user.getUserName();
                    modelAndView.addObject("flag", 1);
                    modelAndView.addObject("message", "登录成功");
                    modelAndView.addObject("userId", userId);
                    modelAndView.addObject("userName", userName);

                    modelAndView.setViewName("ChatRoom");
                    //modelAndView.setViewName("testLogin");

                    System.out.println(user.getUserName() + "登录");

                    //向浏览器传递用户信息
                    httpSession.setAttribute("userId", userId);
                    httpSession.setAttribute("userName", userName);
                }else {//此账号在线
                    modelAndView.addObject("flag", 0);
                    modelAndView.addObject("message", "该用户已经登录");
                    modelAndView.setViewName("LoginError");
                }
            }else
            {
                modelAndView.addObject("flag", 0);
                modelAndView.addObject("message", "用户名或密码错误、或该用户不存在");
                modelAndView.setViewName("LoginError");
            }
        }

        return modelAndView;
    }

    @Autowired
    SignupService signupService;

    @Autowired
    UserService userService;

    /**注册
     * @return ModelAndView*/
    @PostMapping("/signup")
    public ModelAndView signup(UserSignup userSignup)
    {
        ModelAndView modelAndView = new ModelAndView();
        String userName = userSignup.getUserName();
        String userId = userSignup.getUserId();
        String password = userSignup.getPassword();

        //如果输入有空值
        if (Objects.equals(userName, "") || Objects.equals(userId, "") || Objects.equals(password, ""))
        {
            modelAndView.setViewName("SignupError");
            modelAndView.addObject("message", "账号、名字或密码未输入");
        } else
        {
            if (password.length() < 6)
            {//如果账号密码位数低于6位
                modelAndView.setViewName("SignupError");
                modelAndView.addObject("message", "密码不低于6个字节");
            } else if(userId.length() < 6)
            {//如果账号低于6位
                modelAndView.setViewName("SignupError");
                modelAndView.addObject("message", "账号不低于6个字节");
            } else if(userName.length() < 2)
            {//如果名字低于4位
                modelAndView.setViewName("SignupError");
                modelAndView.addObject("message", "名字不低于2位");
            } else {//检查该账号是否已经被注册
                String name = userService.getUserNameById(userId);
                if(name == null || name.length() < 2)
                {
                    //满足条件，进行注册
                    boolean a = signupService.signupUserData(userName, userId, password);
                    //注册成功
                    modelAndView.setViewName("Login");
                } else
                {
                    //注册出错
                    modelAndView.setViewName("SignupError");
                    modelAndView.addObject("message", "账号已经存在");
                }
            }
        }
        return modelAndView;
    }
}