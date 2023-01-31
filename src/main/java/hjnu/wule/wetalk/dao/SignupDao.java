package hjnu.wule.wetalk.dao;

//汉江师范学院 数计学院 吴乐创建于2022/12/26 21:58:16

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**用户注册
 * @author 吴乐*/
@Mapper
public interface SignupDao
{
    @Insert("insert into user (userId, userName, password) value(#{userId},#{userName},#{password})")
    boolean signupUserData(String userName,String userId,String password);
}
