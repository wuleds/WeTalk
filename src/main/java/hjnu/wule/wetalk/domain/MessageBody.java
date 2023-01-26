package hjnu.wule.wetalk.domain;

//汉江师范学院 数计学院 吴乐创建于2022/12/26 14:16:37

/**
 浏览器发送给服务器的消息数据
 */
public class MessageBody
{
    private String toId;//如果toId为all，则推送给所有人
    private String message;
    private String formId;

    public MessageBody(){}

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }
}
