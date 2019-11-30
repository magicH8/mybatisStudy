package day01.dao;

import day01.model.User;

public interface UserDao {
    public void save(User user);

    public User findUserById(int id);

}
