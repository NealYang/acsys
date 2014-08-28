package com.acsys.grouping.action;

import java.util.ArrayList;
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
	private List<User> users = new ArrayList<User>();
	private List<User> allUsers = new ArrayList<User>();

	@Resource
	private IGroupingService groupingService;
	@Resource
	private IUserService userService;

	@Override
	public void prepare() throws Exception {
		getAllGroupings();
		allUsers = userService.getAllUsers();
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
			setUsers();
		}
		return "grouping-form";
	}

	private void setUsers() {
		if (!Utils.isEmpty(groupingId)) {
			users = userService.getUsersForGrouping(groupingId);
			for (int i = allUsers.size() - 1; i >= 0; i--) {
				for (int j = 0; j < users.size(); j++) {
					if (users.get(j).getId().equals(allUsers.get(i).getId())) {
						allUsers.remove(i);
						break;
					}
				}
			}
		}
	}

	public String saveGrouping() {
		if (!Utils.isEmpty(grouping.getId())) {
			// TODO:add delete users
			groupingId = grouping.getId();
			groupingService.updateGrouping(grouping, null, null);
		} else {
			// TODO:add users
			groupingId = groupingService.addGrouping(grouping.getName(), grouping.getRemark(), null);
		}
		setUsers();
		return "grouping-form";
	}

	public String deleteGrouping() {
		if (Utils.isEmpty(groupingId)) {
			return INPUT;
		}

		groupingService.deleteGrouping(groupingId);
		return INPUT;
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

	public void setGrouping(Grouping grouping) {
		this.grouping = grouping;
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