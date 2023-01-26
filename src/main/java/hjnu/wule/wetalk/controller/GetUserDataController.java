package hjnu.wule.wetalk.controller;

//汉江师范学院 数计学院 吴乐创建于2023/1/5 23:12:45

import hjnu.wule.wetalk.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

/**
 *
 */
@Controller
@RequestMapping("/getUserData")
public class GetUserDataController
{
    static {
        System.out.println("GetUserDataController Ready...");
    }
    @Autowired
    UserService userService;

    @RequestMapping("/getUserNameById")
    @ResponseBody
    public String getUserName(HttpSession httpSession)
    {
        System.out.println("GetUserDataController.getUserName function start running");

        String userId = (String) httpSession.getAttribute("userId");
        String userName = userService.getUserNameById(userId);

        System.out.println(userId);
        System.out.println(userName);

        System.out.println("GetUserDataController.getUserName function end run");

        return userName;
    }

    @RequestMapping("/getOnlineCount")
    @ResponseBody
    public String getOnlineCount()
    {
        System.out.println("GetUserDataController.getOnlineCount function start running");
        return String.valueOf(WebSocketServer.getOnlineCount());
    }

    @RequestMapping("/getOnlineUserData")
    @ResponseBody
    public String[][] getOnlineUserIds()
    {
        String[][] userData = new String[10][2];
        Set<String> userIdSet= WebSocketServer.getOnlineUserIdSet();

        int i = 1;

        for(String id:userIdSet)
        {
            userData[i][0] = id;
            userData[i][1] = userService.getUserNameById(id);
            i++;
        }

        //数组第一行存储在线人数。
        userData[0][0] = String.valueOf(i-1);

        return userData;
    }

}
