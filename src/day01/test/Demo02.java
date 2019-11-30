package day01.test;

import day01.dao.UserDao;
import day01.dao.impl.UserDaoImpl;
import day01.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class Demo02 {
    SqlSessionFactory sessionFactory;

    @Before
    public void before() throws IOException {
        System.out.println("before--------------------获得session");
        //读取配置文件
        String resource = "SqlMapConfig.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        //1通过SqlSessionFactoryBuilder创建SqlSessionFactory会话工厂
        sessionFactory = new SqlSessionFactoryBuilder().build(is);
    }


    /**
     * 查询一条结果与多条结果
     */
    @Test
    public void test1() {
        //调用dao
        //1.创建dao
        UserDao userDao = new UserDaoImpl(sessionFactory);
        User user = userDao.findUserById(1);
        System.out.println("user = " + user);

        User user1 = new User("U156", "2", new Date(), "德意志");
        userDao.save(user1);
        System.out.println("id=" + user1.getId());

    }
}

