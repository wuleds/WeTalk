package hjnu.wule.wetalk.dao;

//汉江师范学院 数计学院 吴乐创建于2023/1/26 0:48:38

/**
 * @作用
 */
public interface LogDao
{
    boolean privateChatLog();
    boolean publicRoomLog();
    boolean userLoginLog();
    boolean systemMessageLog();

}
