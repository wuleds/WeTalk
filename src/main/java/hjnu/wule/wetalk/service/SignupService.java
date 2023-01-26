package hjnu.wule.wetalk.service;

//汉江师范学院 数计学院 吴乐创建于2022/12/27 22:03:31

import hjnu.wule.wetalk.dao.SignupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**注册业务*/
@Service
public class SignupService
{
    @Autowired
    SignupDao signupDao;

    public boolean signupUserData(String userName,String userId,String password)
    {
        return signupDao.signupUserData(userName,userId,password);
    }

}
