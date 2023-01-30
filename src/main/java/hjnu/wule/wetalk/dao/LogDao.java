package hjnu.wule.wetalk.dao;

//汉江师范学院 数计学院 吴乐创建于2022/12/27 00:48:38

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**存日志的d*/
@Mapper
public interface LogDao
{
    @Insert("insert into private_chat_message_log (formId, toId, message, sendDate, code) value (#{formId},#{toId},#{message},#{sendDate},#{code})")
    boolean privateChatLog(String formId,String toId,String message,String sendDate,String code);

    @Insert("insert into public_room_message_log (formId, message, sendDate, code) value (#{formId},#{message},#{sendDate},#{code})")
    boolean publicRoomLog(String formId,String message,String sendDate,String code);

    @Insert("insert into user_login_log (userId, userName, loginDate) value (#{userId},#{userName},#{loginDate})")
    boolean userLoginLog(String userId,String userName,String loginDate);

    @Update("update user_login_log set lineDate = #{lineDate} where userId = #{userId} and loginDate = #{loginDate}")
    boolean userLineLog(String lineDate,String userId,String loginDate);

    @Insert("insert into system_message_log (message, sendDate) value (#{message},#{sendDate})")
    boolean systemMessageLog(String message,String sendDate);
}
