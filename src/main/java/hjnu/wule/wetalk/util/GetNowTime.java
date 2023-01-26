package hjnu.wule.wetalk.util;

//汉江师范学院 数计学院 吴乐创建于2022/12/27 20:52:16

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取当前系统时间
 */

public class GetNowTime
{
    public static String getNowTime()
    {
        Date nowTime = new Date(System.currentTimeMillis());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return simpleDateFormat.format(nowTime);

    }
}
