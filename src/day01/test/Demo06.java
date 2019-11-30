package day01.test;

import day01.mapper.UserMapper;
import day01.model.User;
import day01.vo.UserQueryVO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo06 {
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
        //通过模型的包装类查询用户
        UserQueryVO vo = new UserQueryVO();
        User user = new User();
        user.setSex("1");
        vo.setUser(user);
        int userCount = mapper.findUserCount(vo);
        System.out.println("userCount = " + userCount);

    }

    /**
     * 设置返回数据为resultMap
     */
    @Test
    public void test2() {
        UserMapper mapper = session.getMapper(UserMapper.class);
        User userByIdResultMap = mapper.findUserByIdResultMap(16);
        System.out.println("userByIdResultMap = " + userByIdResultMap);

    }

    @Test
    public void test3() {
        UserMapper mapper = session.getMapper(UserMapper.class);

        UserQueryVO vo = new UserQueryVO();
        User user = new User();
//        user.setSex("1");
        user.setUsername("张");
        vo.setUser(user);
        List<User> userList = mapper.findUserList(vo);
        System.out.println("userList = " + userList);


    }

    @Test
    public void test4() {
        UserMapper mapper = session.getMapper(UserMapper.class);

        UserQueryVO vo = new UserQueryVO();
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(10);
        ids.add(16);
        vo.setIds(ids);
        List<User> userByIds = mapper.findUserByIds(vo);
        System.out.println("userByIds = " + userByIds);


    }

    @Test
    public void test5() {
        UserMapper mapper = session.getMapper(UserMapper.class);

        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(10);
        ids.add(16);
        List<User> userByIds = mapper.findUserByIds2(ids);
        System.out.println("userByIds = " + userByIds);


    }
}

