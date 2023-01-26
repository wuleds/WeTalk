package hjnu.wule.wetalk.domain;

//汉江师范学院 数计学院 吴乐创建于2022/12/26 15:10:07

/**
 * @作用
 */
public class UserLogin
{
    private String userId;
    private String password;

    public UserLogin(){}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
