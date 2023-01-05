package hjnu.wule.wetalk.controller.websocket;

//汉江师范学院 数计学院 吴乐创建于2022/12/31 17:51:58

import hjnu.wule.wetalk.config.GetHttpSessionConfig;
import hjnu.wule.wetalk.util.MakeMessageUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 建立websocket连接
 * 路径:ws://localhost/chatLink
 */

@Component
@ServerEndpoint("/chatLink")
public class ChatServerEndpoint
{
    //在线人数
    private static int onlineCount = 0;

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
        addOnlineCount();

        System.out.println("1");
        this.session = session;
        HttpSession httpSession = (HttpSession) endpointConfig.getUserProperties().get(HttpSession.class.getName());
        this.httpSession = httpSession;

        //从httpSession对象中获取用户id,用来表示唯一的用户。
        String userId = (String) httpSession.getAttribute("userId");

        //将当前对象存储到容器中
        onlineUser.put(userId,this);

        System.out.println("2");
        //将当前登录的用户的用户名推送给所有客户端。
        //1.生成消息
        String systemMassage = MakeMessageUtil.makeServerMassage(true,null,getUserId());

        //2.调用方法进行系统消息的推送
        pushMessage(systemMassage);
        System.out.println("3");
    }

    private void pushMessage(String message)
    {
        //将消息推送给所有在线的客户端
        try {
            Set<String> userIds = onlineUser.keySet();

            for (String id : userIds) {
                ChatServerEndpoint chatServerEndpoint = onlineUser.get(id);
                chatServerEndpoint.session.getBasicRemote().sendText(message);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private Set<String> getUserId()
    {
        return onlineUser.keySet();
    }

    @OnMessage
    public void onMessage(String message,Session session)
    {

    }

    @OnClose
    public void onClose(Session session)
    {
        subOnlineCount();
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        ChatServerEndpoint.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        ChatServerEndpoint.onlineCount--;
    }

    public static Map<String, ChatServerEndpoint> getWebSocketSet() {
        return onlineUser;
    }

}
