package hjnu.wule.wetalk.domain;

//汉江师范学院 数计学院 吴乐创建于2022/12/26 14:35:02

/**
 * @作用 用于服务端转发消息
 * @参数 code 标记，前端通过code识别是系统消息、群消息、私聊消息
 * @参数 date 消息的时间
 * @参数 messageBody 消息体，里面存放了消息、发送者和发送目标
 * code为0，则messageBody只存放message
 * code为1，则存满
 * code为2，则messageBody中的message存放图片的名字
 */
public class ServerMessage
{
    private String code;//0表示系统消息，1表示普通消息，2表示图片
    private String date;
    private MessageBody messageBody;

    public ServerMessage(){}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public MessageBody getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(MessageBody messageBody) {
        this.messageBody = messageBody;
    }
}
