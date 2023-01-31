package hjnu.wule.wetalk.domain;

//汉江师范学院 数计学院 吴乐创建于2022/12/26 14:55:07

/**
 用户类,存放用户名和Id
 * @author 吴乐
 */
public class User
{
    private String userId;
    private String userName;

    public User(){}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
