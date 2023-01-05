package hjnu.wule.wetalk.domain;

//汉江师范学院 数计学院 吴乐创建于2022/12/27 14:22:20

/**
 用于浏览器登录，确定是否登录成功。
 */
public class DoResult
{
    private boolean flag;
    private String message;

    public DoResult(){}


    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
