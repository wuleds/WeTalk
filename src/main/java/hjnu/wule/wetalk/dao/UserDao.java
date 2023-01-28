package hjnu.wule.wetalk.dao;

//汉江师范学院 数计学院 吴乐创建于2022/12/26 23:16:45

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**对用户的数据进行查询*/
@Mapper
public interface UserDao
{
    @Select("select userName from user where userId = #{userId}")
    String getUserNameById(@Param("userId") String id);

}
