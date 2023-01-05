package hjnu.wule.wetalk;

//汉江师范学院 数计学院 吴乐创建于2023/1/5 20:55:14

import com.fasterxml.jackson.core.JsonProcessingException;
import hjnu.wule.wetalk.domain.ServerMessage;
import hjnu.wule.wetalk.domain.User;
import hjnu.wule.wetalk.util.GetNowTime;
import hjnu.wule.wetalk.util.MakeMessageUtil;
import org.junit.jupiter.api.Test;

/**
 * @作用
 */
public class timeTest
{
    @Test
    public static void main(String[] args) throws JsonProcessingException {
        User user = new User();
        user.setUserId("id");
        user.setPassword("123456");
        System.out.println(GetNowTime.getNowTime());
        System.out.println(MakeMessageUtil.makeServerMassage(true,null,user));
    }

}
