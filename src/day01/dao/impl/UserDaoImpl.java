package day01.dao.impl;

import day01.dao.UserDao;
import day01.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserDaoImpl implements UserDao {
    private SqlSessionFactory sessionFactory;

    public UserDaoImpl(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserDaoImpl() {
    }

    @Override
    public void save(User user) {
        //获取session
        SqlSession session = sessionFactory.openSession();
        //插入数据
        session.insert("insertUser2", user);
        //提交事务
        session.commit();
        //关闭session
        session.close();

    }

    @Override
    public User findUserById(int id) {
        //获取session
        SqlSession session = sessionFactory.openSession();
        //查询数据
        User user = session.selectOne("findUserById", id);
        //关闭session
        session.close();
        return user;
    }
}
