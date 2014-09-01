package com.acsys.account.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.acsys.account.dao.IUserDao;
import com.acsys.account.model.User;

/**
 * @author Nealy
 * @date Aug 6, 2014
 */
@Repository
public class UserDao implements IUserDao {
	@Resource
	private SqlSession sqlSession;

	public User getUserById(String id) {
		User user = sqlSession.selectOne("getUserById", id);
		return user;
	}

	public List<User> getUserByIds(String ids) {
		List<User> list = sqlSession.selectList("getUserByIds", ids);
		return list;
	}

	public User getUserByEmail(String email) {
		List<User> list = sqlSession.selectList("getUserByEmail", email);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public List<User> getAllUsers() {
		List<User> list = sqlSession.selectList("getAllUsers");
		return list;
	}

	public List<User> getUsersForGrouping(String groupingId) {
		List<User> list = new ArrayList<User>();
		list = sqlSession.selectList("getUsersForGrouping", groupingId);
		return list;
	}

	public String addUser(User user) {
		sqlSession.insert("addUser", user);
		return user.getId();
	}

	public void updateUser(User user) {
		sqlSession.update("updateUser", user);
	}
}