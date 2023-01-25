package hjnu.wule.wetalk.controller;

//汉江师范学院 数计学院 吴乐创建于2022/12/31 17:51:58

import hjnu.wule.wetalk.util.GetNowTime;
import hjnu.wule.wetalk.util.MakeMessageUtil;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 建立websocket连接
 * 路径:ws://localhost/linkRoom
 */

@ServerEndpoint("/linkRoom")
@Component
public class WebSocketServer
{
    static{
        System.out.println("WebSocketServer Ready...");
    }
    //在线人数
    private static int onlineCount = 0;

    //用来存储对应用户的ChatServerEndpoint对象
    private static final Map<String, WebSocketServer> onlineUser = new ConcurrentHashMap<>();

    //声明Session对象，通过该对象可以发送消息给指定的用户
    private Session session;

    //声明一个httpSess对象，在httpSession中存储了用户ID。
    private HttpSession httpSession;

    @OnOpen
    //建立连接时调用
    public void onOpen(Session session, EndpointConfig endpointConfig)
    {
        System.out.println("websocket on open start running");
        addOnlineCount();


        this.session = session;
        //HttpSession httpSession = (HttpSession) endpointConfig.getUserProperties().get(HttpSession.class.getName());
        //this.httpSession = httpSession;

        //从httpSession对象中获取用户id,用来表示唯一的用户。
       // String userId = (String) httpSession.getAttribute("userId");

        //将当前对象存储到容器中
        onlineUser.put(session.getId(),this);

        //将当前登录的用户的用户名推送给所有客户端。
        //1.生成消息
        String systemMassage = "用户"+session.getId()+"上线";

        //2.调用方法进行系统消息的推送
        pushMessage(GetNowTime.getNowTime()+"  "+systemMassage);
        System.out.println("websocket on open end run");
    }

    @OnMessage
    public void onMessage(String message,Session session) throws IOException
    {
        System.out.println("websocket onMessage start push message");

        pushMessage(GetNowTime.getNowTime()+session.getId()+"说"+message);
        System.out.println(GetNowTime.getNowTime()+"  "+session.getId()+"说"+message);
        System.out.println("websocket onMessage end push message");
    }

    @OnClose
    public void onClose(Session session)
    {
        System.out.println("websocket close");
        //用户下线后需要移除与该用户建立的WebSocketServer对象，否则该对象会继续执行，然后报错。
        onlineUser.remove(session.getId());
        pushMessage(GetNowTime.getNowTime()+"  用户"+session.getId()+"下线");
        subOnlineCount();
    }

    @OnError
    public void onError(Throwable throwable)
    {
        System.out.println(throwable.getMessage());
    }

    private void pushMessage(String message)
    {
        //将消息推送给所有在线的客户端
        try {
            Set<String> userIds = onlineUser.keySet();

            for (String id : userIds) {
                WebSocketServer chatServerEndpoint = onlineUser.get(id);
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

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    public static Map<String, WebSocketServer> getWebSocketSet() {
        return onlineUser;
    }

}
