package com.acsys.account.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.acsys.account.dao.IUserDao;
import com.acsys.account.model.User;
import com.acsys.account.service.IUserService;
import com.acsys.common.Utils;
import com.acsys.grouping.dao.IGroupingDao;
import com.acsys.grouping.model.Grouping;

/**
 * @author Nealy
 * @date Aug 6, 2014
 */
@Service
public class UserService implements IUserService {
	@Resource
	private IUserDao userDao;
	@Resource
	private IGroupingDao groupingDao;

	public User getUserById(String id) {
		if (Utils.isEmpty(id)) {
			return null;
		}
		return userDao.getUserById(id);
	}

	public List<User> getUserByIds(String[] ids) {
		if (ids == null || ids.length < 1) {
			return new ArrayList<User>();
		}
		return userDao.getUserByIds(Utils.toMySQLString(ids));
	}

	public User getUserByEmail(String email) {
		if (Utils.isEmpty(email)) {
			return null;
		}
		return userDao.getUserByEmail(email);
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	public List<User> getUsersForGrouping(String groupingId) {
		if (Utils.isEmpty(groupingId)) {
			return new ArrayList<User>();
		}
		return userDao.getUsersForGrouping(groupingId);
	}

	public String addUser(String email, String password, String groupingId) {
		User oldUser = this.getUserByEmail(email);
		if (oldUser != null) {
			return "";
		}

		User user = new User();
		String name = "";
		if (email.contains("@")) {
			name = email.substring(0, email.indexOf("@"));
		}
		user.setId(null);
		user.setName(name);
		user.setEmail(email);
		user.setPassword(Utils.MD5(password));
		user.setAmount(0);

		if (!Utils.isEmpty(groupingId)) {
			Grouping grouping = groupingDao.getGroupingById(groupingId);
			if (!Utils.isEmpty(grouping)) {
				user.setGroupingId(groupingId);
				user.setGroupingName(grouping.getName());
			}
		}

		user.setCreated(new Date());
		user.setLastTime(new Date());

		return userDao.addUser(user);
	}

	public void updateUser(User user) {
		if (Utils.isEmpty(user)) {
			return;
		}
		userDao.updateUser(user);
	}

	public boolean isUserExist(String email) {
		if (Utils.isEmpty(email)) {
			return false;
		}
		User user = userDao.getUserByEmail(email);
		return user != null;
	}
}