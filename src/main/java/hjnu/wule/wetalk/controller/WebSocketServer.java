package hjnu.wule.wetalk.controller;

//汉江师范学院 数计学院 吴乐创建于2022/12/26 14:51:58

import com.alibaba.fastjson.JSON;
import hjnu.wule.wetalk.domain.MessageBody;
import hjnu.wule.wetalk.domain.ServerMessage;
import hjnu.wule.wetalk.domain.User;
import hjnu.wule.wetalk.service.LogService;
import hjnu.wule.wetalk.util.GetNowTime;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
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
    private Session serverSession;

    private HttpSession httpSession;

    String userId;
    String userName;
    String loginDate;

    @Autowired
    LogService logService;

    @OnOpen
    //建立连接时调用
    public void onOpen(String a,User user,Session session)
    {
        System.out.println("websocket on open start running");
        //在线数加1
        addOnlineCount();

        //获取用户信息
        userId = user.getUserId();
        userName = user.getUserName();
        //获取登录时间
        loginDate = GetNowTime.getNowTime();

        if(userId == null || userName == null)
        {
            System.out.println("onOpen,用户名或账号为空");
            return;
        }

        System.out.println("onOpen,上线提醒：userId:"+userId+"userName"+userName);

        //获取session
        serverSession = session;

        //将当前对象存储到容器中,方便发消息。
        onlineUser.put(userId,this);

        //记录日志
        logService.userLoginLog(userId,userName,loginDate);

        //将当前登录的用户的用户名推送给所有客户端。
        //1.生成消息,系统消息，存有消息码，时间，和消息体
        ServerMessage serverMessage = new ServerMessage();
        serverMessage.setCode("0");
        serverMessage.setDate(GetNowTime.getNowTime());
        MessageBody messageBody = new MessageBody();
        messageBody.setMessage("用户"+ userName +"上线了");
        serverMessage.setMessageBody(messageBody);

        //2.调用方法进行系统消息的推送
        sendMessage(serverMessage);
        System.out.println("websocket on open end run");
    }

    @OnMessage
    public void onMessage(ServerMessage serverMessage,Session session)
    {
        System.out.println("websocket onMessage start push message");

        String toId = serverMessage.getMessageBody().getToId();
        String formId = serverMessage.getMessageBody().getFormId();
        String message = serverMessage.getMessageBody().getMessage();
        String nowTime = GetNowTime.getNowTime();
        String code = serverMessage.getCode();

        //若为群聊消息，则存群聊日志表
        if(Objects.equals(toId, "all"))
        {
            logService.publicRoomLog(formId,message,nowTime,code);
        }else {
            //若为私聊消息，则存私聊日志表
            logService.privateChatLog(formId,toId,message,nowTime,code);
        }
        //发送消息
        sendMessage(serverMessage);

        System.out.println("websocket onMessage end push message");
    }

    @OnClose
    public void onClose(Session session)
    {
        System.out.println("websocket close");


        //用户下线后需要移除与该用户建立的WebSocketServer对象，否则该对象会继续执行，然后报错。
        onlineUser.remove(session.getId());

        String nowTime = GetNowTime.getNowTime();
        logService.userLineLog(nowTime,userId,loginDate);

        //将当前下线的用户的用户名推送给所有客户端。
        //1.生成消息,系统消息，存有消息码，时间，和消息体
        ServerMessage serverMessage = new ServerMessage();
        serverMessage.setCode("0");
        serverMessage.setDate(GetNowTime.getNowTime());
        MessageBody messageBody = new MessageBody();
        messageBody.setMessage("用户"+ userName +"下线了");
        serverMessage.setMessageBody(messageBody);

        //2.调用方法进行系统消息的推送
        sendMessage(serverMessage);

        subOnlineCount();
    }

    @OnError
    public void onError(Throwable throwable)
    {
        System.out.println(throwable.getMessage());
    }

    private void sendMessage(ServerMessage serverMessage)
    {

        String code = serverMessage.getCode().trim();
        String formId = serverMessage.getMessageBody().getFormId().trim();
        String toId = serverMessage.getMessageBody().getToId().trim();

        String message;

        //若为系统消息，或者为普通群聊消息则推送给所有人。
        if(Objects.equals(code, "0") || Objects.equals(toId, "all"))
        {
            Set<String> userIds = getOnlineUserIdSet();
            message = JSON.toJSONString(serverMessage);
            if(Objects.equals(code, "0") )
            {
                //记录系统消息
                logService.systemMessageLog(serverMessage.getMessageBody().getMessage(),GetNowTime.getNowTime());
            }

            for (String id : userIds)
            {
                //将要发送的消息对象转换为json格式。
                //发送json。
                try {
                    onlineUser.get(id).serverSession.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if(Objects.equals(code, "1") || Objects.equals(code, "2"))
        {
            message = JSON.toJSONString(serverMessage);
            //若为普通私聊消息，或者图片,则推送给私聊双方
            try {
                onlineUser.get(formId).serverSession.getBasicRemote().sendText(message);
                onlineUser.get(toId).serverSession.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            //出错
            String mes = serverMessage.getMessageBody().getMessage();
            mes = "消息:{" + mes + "}发送失败";
            serverMessage.getMessageBody().setMessage(mes);
            message = JSON.toJSONString(serverMessage);

            try {
                onlineUser.get(formId).serverSession.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static synchronized Set<String> getOnlineUserIdSet()
    {
        return WebSocketServer.onlineUser.keySet();
    }

    public static synchronized int getOnlineCount() {
        return WebSocketServer.onlineCount;
    }

    private static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    private static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
