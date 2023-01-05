package hjnu.wule.wetalk.util;

//汉江师范学院 数计学院 吴乐创建于2023/1/5 20:22:29

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hjnu.wule.wetalk.domain.ServerMessage;

/**
 * 制造消息，将参数json格式化
 */
public class MakeMessageUtil
{
    public static String makeServerMassage(boolean isSystemMassage,String fromId,Object message) throws JsonProcessingException
    {
        ServerMessage serverMessage = new ServerMessage();
        String systemMassage = null;
        try
        {
            if(isSystemMassage)
            {
                serverMessage.setFromId("123456");
            }else
            {
                serverMessage.setFromId(fromId);
            }
            serverMessage.setIsSystem(isSystemMassage);
            serverMessage.setMessage(message);
            serverMessage.setTime(GetNowTime.getNowTime());

            ObjectMapper objectMapper = new ObjectMapper();
            systemMassage = objectMapper.writeValueAsString(serverMessage);

        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            System.out.println(systemMassage);
        }
        return systemMassage;
    }
}
