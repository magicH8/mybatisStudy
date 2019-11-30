package day01.mapper;

import day01.model.User;
import day01.vo.UserQueryVO;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    /**
     * @param user
     * @return 返回受影响的行数
     */
    public int save(User user);

    public User findUserById(int id);

    public List<User> findUserByUserQueryVO(UserQueryVO vo);

    public List<User> findUserByMap(Map<String, Object> map);

    /**
     * 返回用户的个数
     *
     * @param vo
     * @return
     */
    public int findUserCount(UserQueryVO vo);

    public User findUserByIdResultMap(int userId);

    /**
     * mybatis的if和where使用
     *
     * @return
     */
    public List<User> findUserList(UserQueryVO vo);

    /**
     * 查找多个id的用户数据
     *
     * @param vo
     * @return
     */
    public List<User> findUserByIds(UserQueryVO vo);

    public List<User> findUserByIds2(List<Integer> ids);
}

