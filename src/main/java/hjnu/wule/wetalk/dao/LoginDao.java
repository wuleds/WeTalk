package hjnu.wule.wetalk.dao;

//汉江师范学院 数计学院 吴乐创建于2022/12/21 22:05:02

import hjnu.wule.wetalk.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginDao
{
    /**登录判定*/
    @Select("select userId,userName from user where userId = #{userId} and password = #{password}")
    User login(@Param("userId") String userId, @Param("password")String password);

}
