package com.acsys.account.dao;

import java.util.List;

import com.acsys.account.model.User;

/**
 * @author Nealy
 * @date Jul 29, 2014
 */
public interface IUserDao {
	public User getUserById(String id);

	public List<User> getUserByIds(String ids);

	public User getUserByEmail(String email);

	public List<User> getAllUsers();

	public List<User> getUsersForGrouping(String groupingId);

	public String addUser(User user);

	public void updateUser(User user);
}