package day02.test;

import day02.mapper.OrderMapper;
import day02.mapper.UserMapper;
import day02.model.OrderDetail;
import day02.model.Orders;
import day02.model.OrdersExt;
import day02.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Demo1 {
    SqlSession session;

    @Before
    public void before() throws IOException {
        System.out.println("before--------------------获得session");
        //读取配置文件
        String resource = "day02/SqlMapConfig.xml";
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
     * 一对一,写个订单的扩展类
     */
    @Test
    public void test1() {
        OrderMapper mapper = session.getMapper(OrderMapper.class);
        OrdersExt orderById = mapper.findOrderById(3);
        System.out.println("orderById = " + orderById);

    }

    /**
     * 一对一,模型里面有模型
     */
    @Test
    public void test2() {
        OrderMapper mapper = session.getMapper(OrderMapper.class);
        Orders orderById2 = mapper.findOrderById2(3);
        System.out.println("orderById2 = " + orderById2);
        System.out.println("orderById2 = " + orderById2.getUser());
    }

    /**
     * 一对一,模型里面有集合
     */
    @Test
    public void test3() {
        OrderMapper mapper = session.getMapper(OrderMapper.class);
        Orders orderById2 = mapper.findOrderById3(3);
        System.out.println("orderById2 = " + orderById2);
        System.out.println("getUser = " + orderById2.getUser());
        System.out.println("getOrderDetails = " + orderById2.getOrderDetails().toString());
    }

    /**
     * 多对多
     */
    @Test
    public void test4() {
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> users = mapper.findUserAndOrderInfo();
        for (User user : users) {
            System.out.println("用户信息:" + user);
            for (Orders order : user.getOrders()) {
                System.out.println("订单信息:" + order);
                System.out.println("订单详情信息:");
                for (OrderDetail orderDetail : order.getOrderDetails()) {
                    System.out.println(orderDetail);
                    System.out.println("商品信息:" + orderDetail.getItems());
                }
                System.out.println("==========================");
            }

        }

    }

    /**
     * 懒加载
     */
    @Test
    public void test5() {
        OrderMapper mapper = session.getMapper(OrderMapper.class);
        List<Orders> list = mapper.findOrderAndUserByLazyloading();
        for (Orders orders : list) {
            System.out.println("orders = " + orders);
            System.out.println("订单所属用户:" + orders.getUser());
        }


    }

}
















