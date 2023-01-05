package hjnu.wule.wetalk.dao;

//汉江师范学院 数计学院 吴乐创建于2023/1/5 23:16:45

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 *
 */
@Mapper
public interface UserDao
{
    @Select("select userName from user where userId = #{userId}")
    String getUserNameById(@Param("userId") String id);

}
