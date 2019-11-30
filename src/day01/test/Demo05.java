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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo05 {
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
        user.setId(1);
        vo.setUser(user);
        List<User> userByUserQueryVO = mapper.findUserByUserQueryVO(vo);
        System.out.println(userByUserQueryVO.toString());

    }

    @Test
    public void test2() {
        UserMapper mapper = session.getMapper(UserMapper.class);

        //查询条件
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张");
        map.put("sex", "2");
        List<User> userByMap = mapper.findUserByMap(map);
        System.out.println("userByMap = " + userByMap);
    }
}

