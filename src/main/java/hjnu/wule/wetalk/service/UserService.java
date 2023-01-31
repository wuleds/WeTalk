package hjnu.wule.wetalk.service;

//汉江师范学院 数计学院 吴乐创建于2022/12/27 23:20:42

import hjnu.wule.wetalk.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**获取用户数据
 * @author 吴乐*/
@Service
public class UserService
{
    @Autowired
    UserDao userDao;

    public String getUserNameById(String id)
    {
        return userDao.getUserNameById(id);
    }
}