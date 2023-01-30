package hjnu.wule.wetalk.controller;

//汉江师范学院 数计学院 吴乐创建于2022/12/28 23:12:45

import hjnu.wule.wetalk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**获取用户相关数据的控制器*/
@CrossOrigin//解决前端跨域问题
@Controller
@RequestMapping("/getUserData")
public class GetUserDataController
{
    static {
        System.out.println("GetUserDataController Ready...");
    }

    @Autowired
    UserService userService;

    /**根据用户id获取用户名*/
    @RequestMapping("/getUserNameById/{userId}")
    @ResponseBody
    public String getUserName(@PathVariable String userId)
    {
        return userService.getUserNameById(userId);
    }

    /**获取在线人数
     * @return String*/
    @RequestMapping("/getOnlineCount")
    @ResponseBody
    public String getOnlineCount()
    {
        return String.valueOf(WebSocketServer.getOnlineCount());
    }

    /**获取在线用户的情况
     * @return Map< String,String >*/
    @RequestMapping("/getOnlineUserData")
    @ResponseBody
    public Map<String,String > getOnlineUserIds()
    {
        Map<String,String> map = new HashMap<>();
        Map<String,String > userIdSet= WebSocketServer.getUserIdAndHttpSessionId();

        Set<String> set = userIdSet.keySet();

        for(String id:set)
        {
            //存储Id，名字
            map.put(id,userService.getUserNameById(id));
        }
        return map ;
    }
}