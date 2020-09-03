package com.duan.dao;

import com.duan.domain.QueryVo;
import com.duan.domain.User;

import javax.management.Query;
import java.util.List;

/**
 * @描述
 * @创建人 Duanhaibo
 * @创建时间 2020/9/2
 * @修改人和其它信息
 */
public interface IUserDao {
    List<User> findAll();
    User findById(Integer id);
    int saveUser(User user);
    int updateUser(User user);
    int deleteUser(Integer id);
    List<User> findByName(String username);
    int count();
    List<User> findByVO(QueryVo vo);
}
