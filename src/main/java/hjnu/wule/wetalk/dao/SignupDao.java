package hjnu.wule.wetalk.dao;

//汉江师范学院 数计学院 吴乐创建于2023/1/25 21:58:16

import hjnu.wule.wetalk.domain.UserSignup;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignupDao
{
    @Insert("insert into user (userId, userName, password) value(#{userId},#{userName},#{password})")
    boolean signupUserData(String userName,String userId,String password);

}
