package com.acsys.account.service;

import java.util.List;

import com.acsys.account.model.User;

/**
 * @author Nealy
 * @date Aug 6, 2014
 */
public interface IUserService {
	public User getUserById(String id);

	public List<User> getUserByIds(String[] ids);

	public User getUserByEmail(String email);

	public List<User> getAllUsers();

	public List<User> getUsersForGrouping(String groupingId);

	public String addUser(String email, String password, String groupId);

	public void updateUser(User user);

	public boolean isUserExist(String email);
}