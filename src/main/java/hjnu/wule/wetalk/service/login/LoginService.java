package hjnu.wule.wetalk.service.login;

//汉江师范学院 数计学院 吴乐创建于2022/12/21 22:14:30

import hjnu.wule.wetalk.dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService
{
    @Autowired
    LoginDao loginDao;

    public boolean login(String userId,String password)
    {
        return loginDao.login(userId, password) != null;
    }

}
