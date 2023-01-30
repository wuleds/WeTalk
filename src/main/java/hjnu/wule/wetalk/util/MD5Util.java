package hjnu.wule.wetalk.util;

//汉江师范学院 数计学院 吴乐创建于2023/1/29 23:35:01

import org.springframework.util.DigestUtils;

/**MD5工具类*/
public class MD5Util
{
    //盐，用于混交md5
    private static final String slat = "&%5123***&&%%$$#@";

    /**生成md5
     * @return String
     */
    public static String getMD5(String str)
    {
        String base = str +"/"+slat;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }
}