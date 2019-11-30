package day01.test;

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

        public class Demo01 {
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


    /**
     * 查询一条结果与多条结果
     */
    @Test
    public void test1() {
        //3调用SqlSession的操作数据库方法
        //查询一条结果
        /*User user = session.selectOne("findUserById", 10);
        System.out.println(user);*/
        //查询多条结果
        List<User> list = session.selectList("findUserByName", "张");
        System.out.println(list);
    }

    /**
     * 插入数据
     */
    @Test
    public void test2() {
        User user = new User("U4702", "2", new Date(), "德国");
        int row = session.insert("insertUser", user);
        System.out.println("row = " + row);
        session.commit();//事务提交
    }

    /**
     * 删除数据
     */
    @Test
    public void test3() {
        int row = session.delete("deleteUser", 38);
        System.out.println("row = " + row);
        session.commit();
    }

    /**
     * 更新数据
     */
    @Test
    public void test4() {
        User user = new User();
        user.setId(32);
        user.setUsername("U81");
        user.setBirthday(new Date());
        user.setAddress("德意志");
        int row = session.update("updateUser", user);
        session.commit();
    }

    /**
     * 插入数据后,往模型里设置id
     */
    @Test
    public void test5() {
        User user = new User("U4703", "2", new Date(), "德国");
        int row = session.insert("insertUser2", user);
        session.commit();//事务提交
        System.out.println("id" + user.getId());
    }
}

