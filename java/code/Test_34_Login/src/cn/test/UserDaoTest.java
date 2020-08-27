package cn.test;

import cn.dao.UserDao;
import cn.domain.User;
import org.junit.Test;


public class UserDaoTest {

    @Test
    public void testLogin(){
        User loginuser = new User();
        loginuser.setUsername("123");
        loginuser.setPassword("123");


        UserDao dao = new UserDao();
        User user = dao.login(loginuser);

        System.out.println(user);
    }
}
