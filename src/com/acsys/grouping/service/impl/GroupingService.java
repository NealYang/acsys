package com.acsys.grouping.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.acsys.account.model.User;
import com.acsys.account.service.IUserService;
import com.acsys.common.Utils;
import com.acsys.core.CommonContext;
import com.acsys.grouping.dao.IGroupingDao;
import com.acsys.grouping.model.Grouping;
import com.acsys.grouping.service.IGroupingService;

/**
 * @author Nealy
 * @date Aug 18, 2014
 */
@Service
public class GroupingService implements IGroupingService {
	@Resource
	private IGroupingDao groupingDao;
	@Resource
	private IUserService userService;

	public Grouping getGroupingById(String id) {
		return groupingDao.getGroupingById(id);
	}

	public List<Grouping> getAllGroupings() {
		return groupingDao.getAllGroupings();
	}

	public String addGrouping(String name, String remark) {
		if (Utils.isEmpty(name)) {
			return "";
		}

		Grouping grouping = new Grouping();
		User currentUser = CommonContext.getCurrentUser();

		grouping.setId(null);
		grouping.setName(name);
		grouping.setMemberNum(0);

		if (!Utils.isEmpty(currentUser)) {
			grouping.setCreatorId(currentUser.getId());
			grouping.setCreatorName(currentUser.getName());
		}

		grouping.setCreated(new Date());
		grouping.setLastTime(new Date());
		grouping.setRemark(remark);

		return groupingDao.addGrouping(grouping);
	}

	public int updateGroupingUser(String groupingId, String[] delUserIds, String[] addUserIds) {
		if (Utils.isEmpty(groupingId)) {
			return 0;
		}
		Grouping grouping = groupingDao.getGroupingById(groupingId);
		if (Utils.isEmpty(grouping)) {
			return 0;
		}

		Map<String, Integer> userMap = new HashMap<String, Integer>();
		int userNum = 0;
		List<User> delUsers = userService.getUserByIds(delUserIds);
		List<User> addUsers = userService.getUserByIds(addUserIds);
		for (User user : delUsers) {
			if (userMap.containsKey(user.getId())) {
				int num = userMap.get(user.getId());
				num--;
				userMap.put(user.getId(), num);
			} else {
				userMap.put(user.getId(), -1);
			}

			String groupingIds = user.getGroupingIds();
			String newGroupingIds = "";
			String[] ids = groupingIds.split(",");
			for (String id : ids) {
				if (!Utils.isEmpty(id) && !id.equals(groupingId)) {
					newGroupingIds += id + ",";
				}
			}
			user.setGroupingIds(newGroupingIds);

			user.setModified(new Date());
			user.setLastTime(new Date());
			userService.updateUser(user);
		}
		for (User user : addUsers) {
			if (userMap.containsKey(user.getId())) {
				int num = userMap.get(user.getId());
				num++;
				userMap.put(user.getId(), num);
			} else {
				userMap.put(user.getId(), 1);
			}

			String groupingIds = user.getGroupingIds();
			groupingIds += groupingId + ",";
			user.setGroupingIds(groupingIds);

			user.setModified(new Date());
			user.setLastTime(new Date());
			userService.updateUser(user);
		}

		for (String key : userMap.keySet()) {
			userNum += userMap.get(key);
		}
		return userNum;
	}

	public void updateGroupingBase(String groupingId, String groupingName, String remark, int userNum) {
		if (Utils.isEmpty(groupingId)) {
			return;
		}
		Grouping grouping = groupingDao.getGroupingById(groupingId);
		if (Utils.isEmpty(grouping)) {
			return;
		}

		if (!Utils.isEmpty(groupingName)) {
			grouping.setName(groupingName);
		}
		grouping.setMemberNum(userNum);
		grouping.setModified(new Date());
		grouping.setLastTime(new Date());
		grouping.setRemark(remark);
		groupingDao.updateGrouping(grouping);
	}

	public void updateGrouping(String groupingId, String groupingName, String remark, String[] delUserIds, String[] addUserIds) {
		int userNum = this.updateGroupingUser(groupingId, delUserIds, addUserIds);
		this.updateGroupingBase(groupingId, groupingName, remark, userNum);
	}
}