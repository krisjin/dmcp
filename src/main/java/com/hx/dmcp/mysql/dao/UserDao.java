package com.hx.dmcp.mysql.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hx.dmcp.mysql.entity.User;

/**
 * @author krisjin
 */
@Repository
public interface UserDao {

	public int addUser(User user);

	public void deleteUsers(@Param("userId") String userId);

	public int deleteUser(@Param("userId") long userId);

	public int updateUser(User user);

	public List<User> getUserWithPage(@Param("offset") long offset, @Param("rows") long rows);

	public int getTotalUserCounts();

	public User getUserById(@Param("userId") long userId);

	public User getUserByEmail(@Param("email") String email);

	public User getUserByName(@Param("userName") String userName);

	public User getUserByPassword(@Param("password") String password);

}
