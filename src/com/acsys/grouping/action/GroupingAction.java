package com.acsys.grouping.action;

import java.util.List;

import javax.annotation.Resource;

import com.acsys.account.model.User;
import com.acsys.account.service.IUserService;
import com.acsys.common.Utils;
import com.acsys.core.BaseAction;
import com.acsys.grouping.model.Grouping;
import com.acsys.grouping.service.IGroupingService;
import com.opensymphony.xwork2.Preparable;

/**
 * @author Nealy
 * @date Jul 30, 2014
 */
public class GroupingAction extends BaseAction implements Preparable {
	private String groupingId;
	private Grouping grouping = new Grouping();
	private List<Grouping> groupings;
	private List<User> users;
	private List<User> allUsers;

	@Resource
	private IGroupingService groupingService;
	@Resource
	private IUserService userService;

	@Override
	public void prepare() throws Exception {
		getAllGroupings();
	}

	@Override
	public String input() {
		if (groupings != null && groupings.size() > 0) {
			groupingId = groupings.get(0).getId();
		}
		return INPUT;
	}

	public String loadGrouping() {
		if (!Utils.isEmpty(groupingId)) {
			grouping = groupingService.getGroupingById(groupingId);
			users = userService.getUsersForGrouping(groupingId);
			allUsers = userService.getAllUsers();
			for (int i = allUsers.size() - 1; i > 0; i--) {
				for (int j = 0; j < users.size(); j++) {
					if (users.get(j).getId().equals(allUsers.get(i).getId())) {
						allUsers.remove(i);
						break;
					}
				}
			}
		}
		return "grouping-form";
	}

	private void getAllGroupings() {
		groupings = groupingService.getAllGroupings();
	}

	public String getGroupingId() {
		return groupingId;
	}

	public void setGroupingId(String groupingId) {
		this.groupingId = groupingId;
	}

	public Grouping getGrouping() {
		return grouping;
	}

	public List<Grouping> getGroupings() {
		return groupings;
	}

	public List<User> getUsers() {
		return users;
	}

	public List<User> getAllUsers() {
		return allUsers;
	}
}