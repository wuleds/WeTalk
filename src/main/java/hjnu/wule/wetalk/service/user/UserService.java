package hjnu.wule.wetalk.service.user;

//汉江师范学院 数计学院 吴乐创建于2023/1/5 23:20:42

import hjnu.wule.wetalk.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @作用
 */

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
