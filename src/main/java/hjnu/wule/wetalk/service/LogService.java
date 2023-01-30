package hjnu.wule.wetalk.service;

//汉江师范学院 数计学院 吴乐创建于2022/12/28 00:10:46

import hjnu.wule.wetalk.dao.LogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**记录日志业务*/
@Service
public class LogService
{
    @Autowired
    LogDao logDao;

    /**记录私聊日志*/
    public boolean privateChatLog(String formId,String toId,String message,String sendDate,String code)
    {
        return logDao.privateChatLog(formId,toId,message,sendDate,code);
    }

    /**记录群聊日志*/
    public boolean publicRoomLog(String formId,String message,String sendDate,String code)
    {
        return logDao.publicRoomLog(formId,message,sendDate,code);
    }

    /**记录用户登录日志*/
    public boolean userLoginLog(String userId,String userName,String loginDate)
    {
        return logDao.userLoginLog(userId,userName,loginDate);
    }

    /**记录用户下线日志*/
    public boolean userLineLog(String lineDate,String userId,String loginDate)
    {
        return logDao.userLineLog(lineDate,userId,loginDate);
    }

    /**记录系统消息日志*/
    public boolean systemMessageLog(String message,String sendDate)
    {
        return logDao.systemMessageLog(message,sendDate);
    }
}