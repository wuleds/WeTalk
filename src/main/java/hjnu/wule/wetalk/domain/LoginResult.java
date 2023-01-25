package hjnu.wule.wetalk.domain;

//汉江师范学院 数计学院 吴乐创建于2022/12/27 14:22:20

/**
 用于浏览器登录，存放是否登录成功和用户Id的数据。
 */
public class LoginResult
{
    private int flag;//0为失败，1为成功
    private User user;

    public LoginResult(){}

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LoginResult{"+ (flag==1?"登录成功":"登录失败")+",名字:"+user.getUserName()+",账号:"+user.getUserId()+"}";
    }
}
