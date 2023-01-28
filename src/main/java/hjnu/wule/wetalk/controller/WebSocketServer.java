package hjnu.wule.wetalk.controller;

//汉江师范学院 数计学院 吴乐创建于2022/12/26 14:51:58

import com.alibaba.fastjson.JSON;
import hjnu.wule.wetalk.config.GetHttpSessionConfig;
import hjnu.wule.wetalk.domain.MessageBody;
import hjnu.wule.wetalk.domain.ServerMessage;
import hjnu.wule.wetalk.service.LogService;
import hjnu.wule.wetalk.util.GetNowTime;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * 建立websocket连接 <br>
 * 路径:ws://localhost/linkRoom
 */
@ServerEndpoint(value = "/linkRoom",configurator = GetHttpSessionConfig.class)
@Component
public class WebSocketServer
{
    static{
        System.out.println("WebSocketServer Ready...");
    }

    private static LogService logService;
    //在线人数
    private static int onlineCount = 0;

    //用来存储对应用户的ChatServerEndpoint对象
    private static final Map<String, WebSocketServer> onlineUser = new ConcurrentHashMap<>();

    //声明Session对象，通过该对象可以发送消息给指定的用户
    private Session session;

    private HttpSession httpSession;

    String httpSessionId;
    String userId;
    String userName;
    String loginDate;

    @Autowired
    public void setLogService(LogService logService)
    {
        WebSocketServer.logService = logService;
    }

    @OnOpen
    public void onOpen(Session session)
    {
        System.out.println("websocket on open start running");

        //1.获取HttpSession，用于标识用户
        httpSession = (HttpSession)session.getUserProperties().get(HttpSession.class.getName());

        //2.检查httpSession
        if(httpSession == null)
        {
            try {
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //3.获取用户信息,获取httpSessionId，用于标识用户，获取登录时间
        userId = (String) httpSession.getAttribute("userId");
        userName = (String) httpSession.getAttribute("userName");
        httpSessionId = httpSession.getId();
        loginDate = GetNowTime.getNowTime();

        System.out.println(userId);
        System.out.println(userName);

        //4.检查账号和Id,如果账号或名字为空，则停止继续连接
        if(userId == null || userName == null)
        {
            try {
                System.out.println("onOpen,id or name is null,连接断开");
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        //5.保存session,用来向浏览器发送消息
        this.session = session;

        //6.将当前对象存储到容器中,方便获取各个用户的session
        onlineUser.put(httpSessionId,this);

        //7.在线数加1
        addOnlineCount();

        //8.记录日志
        logService.userLoginLog(userId,userName,loginDate);

        System.out.println("onOpen,上线提醒:userId:"+userId+",userName:"+userName);

        //9.将当前登录的用户的用户名推送给所有客户端。
        //生成消息,系统消息，存有消息码，时间，和消息体
        ServerMessage serverMessage = new ServerMessage();
        serverMessage.setCode("0");
        serverMessage.setDate(GetNowTime.getNowTime());

        MessageBody messageBody = new MessageBody();
        messageBody.setMessage("用户"+ userName +"上线了");

        serverMessage.setMessageBody(messageBody);

        //调用方法进行系统消息的推送
        sendMessage(serverMessage);
        System.out.println("websocket on open end run");
    }

    @OnMessage
    public void onMessage(String messageJson,Session session)
    {
        System.out.println("websocket onMessage start push message");

        //1.获取json，转为ServerMessage对象
        ServerMessage serverMessage = JSON.parseObject(messageJson,ServerMessage.class);

        //2.获取ServerMessage数据
        String toId = serverMessage.getMessageBody().getToId();
        String formId = serverMessage.getMessageBody().getFormId();
        String message = serverMessage.getMessageBody().getMessage();
        String nowTime = GetNowTime.getNowTime();
        String code = serverMessage.getCode();

        //3.若为群聊消息，则存群聊日志表
        logService = new LogService();
        if(Objects.equals(toId, "all"))
        {
            logService.publicRoomLog(formId,message,nowTime,code);
        }else {
            //4.若为私聊消息，则存私聊日志表
            logService.privateChatLog(formId,toId,message,nowTime,code);
        }
        //5.发送消息
        sendMessage(serverMessage);

        System.out.println("websocket onMessage end push message");
    }

    @OnClose
    public void onClose(Session session)
    {
        System.out.println("websocket close start running");

        //1.检查账号和名字
        if(userId == null || userName == null)
        {
            return;
        }

        //2.用户下线后需要移除与该用户建立的WebSocketServer对象，否则该对象会继续执行，然后报错。
        onlineUser.remove(userId);

        //3.获取下线时间
        String nowTime = GetNowTime.getNowTime();

        //4.记录日志
        logService.userLineLog(nowTime,userId,loginDate);

        //5.将当前下线的用户的用户名推送给所有客户端。
        //生成消息,系统消息，存有消息码，时间，和消息体
        ServerMessage serverMessage = new ServerMessage();
        serverMessage.setCode("0");
        serverMessage.setDate(GetNowTime.getNowTime());
        MessageBody messageBody = new MessageBody();
        messageBody.setMessage("用户"+ userName +"下线了");
        serverMessage.setMessageBody(messageBody);

        //调用方法进行系统消息的推送
        sendMessage(serverMessage);

        //6.在线数-1
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
        String toId = serverMessage.getMessageBody().getToId().trim();

        String message;

        //1.若为系统消息，或者为普通群聊消息则推送给所有人。
        if(Objects.equals(code, "0") || Objects.equals(toId, "all"))
        {
            Set<String> userIds = getOnlineUserIdSet();
            //将要发送的消息对象转换为json格式。
            message = JSON.toJSONString(serverMessage);

            if(Objects.equals(code, "0") )
            {
                //记录系统消息
                logService = new LogService();
                logService.systemMessageLog(serverMessage.getMessageBody().getMessage(),GetNowTime.getNowTime());
            }

            for (String id : userIds)
            {
                //发送json。
                onlineUser.get(id).session.getAsyncRemote().sendText(message);
            }
        }//2.若为私聊消息或者图片
        else if(Objects.equals(code, "1") || Objects.equals(code, "2"))
        {
            String formId = serverMessage.getMessageBody().getFormId().trim();
            message = JSON.toJSONString(serverMessage);
            //若为普通私聊消息，或者图片,则推送给私聊双方
            onlineUser.get(formId).session.getAsyncRemote().sendText(message);
            onlineUser.get(toId).session.getAsyncRemote().sendText(message);
        }else{
            //3.出错,给发送方报错。
            String formId = serverMessage.getMessageBody().getFormId().trim();
            String mes = serverMessage.getMessageBody().getMessage();
            mes = "消息:{" + mes + "}发送失败";
            serverMessage.getMessageBody().setMessage(mes);
            message = JSON.toJSONString(serverMessage);

            onlineUser.get(formId).session.getAsyncRemote().sendText(message);
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
