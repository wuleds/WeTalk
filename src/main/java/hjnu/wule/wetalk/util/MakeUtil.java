package hjnu.wule.wetalk.util;

//汉江师范学院 数计学院 吴乐创建于2022/12/28 18:43:59

import hjnu.wule.wetalk.domain.MessageBody;
import hjnu.wule.wetalk.domain.ServerMessage;

public class MakeUtil
{
    public static ServerMessage makeServerMessage(String code,String nowTime,String fromId,String toId,String message)
    {
        ServerMessage serverMessage = new ServerMessage();
        MessageBody messageBody = new MessageBody();

        serverMessage.setCode(code);
        serverMessage.setDate(nowTime);

        messageBody.setFromId(fromId);
        messageBody.setToId(toId);
        messageBody.setMessage(message);

        serverMessage.setMessageBody(messageBody);

        return serverMessage;
    }
}