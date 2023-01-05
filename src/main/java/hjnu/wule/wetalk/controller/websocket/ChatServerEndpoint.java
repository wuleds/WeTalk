package hjnu.wule.wetalk.controller.websocket;

//汉江师范学院 数计学院 吴乐创建于2022/12/31 17:51:58

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 建立websocket连接
 * 路径:ws://localhost/chatLink
 */

@Component
@ServerEndpoint("/chatLink")
public class ChatServerEndpoint
{
    //用来存储对应用户的ChatServerEndpoint对象
    private static Map<String,ChatServerEndpoint> onlineUser = new ConcurrentHashMap<>();

    //声明Session对象，通过该对象可以发送消息给指定的用户
    private Session session;

    //声明一个httpSess对象，在httpSess中存储了用户ID。
    private HttpSession httpSession;

    @OnOpen
    //建立连接时调用
    public void onOpen(Session session, EndpointConfig endpointConfig)
    {
        this.session = session;
        HttpSession httpSession = (HttpSession) endpointConfig.getUserProperties().get(HttpSession.class.getName());
        this.httpSession = httpSession;

        //从httpSession对象中获取用户id,用来表示唯一的用户。
        String userId = (String) httpSession.getAttribute("userId");

        //将当前对象存储到容器中
        onlineUser.put(userId,this);

        //将当前登录的用户的用户名推送给所有客户端。
        //1.生成消息



    }

    private void pushMessage(String message)
    {

    }

    @OnMessage
    public void onMessage(String message,Session session)
    {

    }

    @OnClose
    public void onClose(Session session)
    {

    }

}
