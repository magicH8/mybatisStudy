package day01.test;

import day01.mapper.UserMapper;
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

public class Demo03 {
    SqlSession session;

    @Before
    public void before() throws IOException {
        System.out.println("before--------------------获得session");
        //读取配置文件
        String resource = "SqlMapConfig.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        //1通过SqlSessionFactoryBuilder创建SqlSessionFactory会话工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //2通过SqlSessionFactory创建SqlSession
        session = sessionFactory.openSession();
    }

    @After
    public void after() {
        System.out.println("after--------------------关闭session");
        //4关闭SqlSession
        session.close();
    }


    @Test
    public void test1() {
        UserMapper mapper = session.getMapper(UserMapper.class);
        User u = mapper.findUserById(1);
        System.out.println("u = " + u);

//        User user1 = new User("俾斯麦", "2", new Date(), "德意志");
//        mapper.save(user1);
//        session.commit();

    }
}

