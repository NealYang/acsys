package com.acsys.grouping.action;

import java.util.ArrayList;
import java.util.Date;
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
	private String addUserIds;// xxx,xxx,xxx,
	private String delUserIds;// xxx,xxx,xxx,

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
		} else {
			// For add
			grouping.setCreated(new Date());
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
			trimUserIds();
			String[] adds = Utils.isEmpty(addUserIds) ? null : addUserIds.split(",");
			String[] dels = Utils.isEmpty(delUserIds) ? null : delUserIds.split(",");
			groupingId = grouping.getId();
			groupingService.updateGrouping(grouping, dels, adds);
			setUsers();
			return "grouping-form";
		} else {
			String[] adds = Utils.isEmpty(addUserIds) ? null : addUserIds.split(",");
			groupingId = groupingService.addGrouping(grouping.getName(), grouping.getRemark(), adds);
			return SUCCESS;
		}
	}

	public String deleteGrouping() {
		if (Utils.isEmpty(groupingId)) {
			return SUCCESS;
		}

		groupingService.deleteGrouping(groupingId);
		return SUCCESS;
	}

	private void getAllGroupings() {
		groupings = groupingService.getAllGroupings();
	}

	private void trimUserIds() {
		String[] adds = Utils.isEmpty(addUserIds) ? null : addUserIds.split(",");
		String addStrings = "";
		String[] dels = Utils.isEmpty(delUserIds) ? null : delUserIds.split(",");
		String delStrings = "";
		if (!Utils.isEmpty(adds)) {
			for (String string : adds) {
				if (!Utils.isEmpty(addStrings)) {
					addStrings += "," + string.trim();
				} else {
					addStrings += string.trim();
				}
			}
		}
		if (!Utils.isEmpty(dels)) {
			for (String string : dels) {
				if (!Utils.isEmpty(delStrings)) {
					delStrings += "," + string.trim();
				} else {
					delStrings += string.trim();
				}
			}
		}
		this.addUserIds = addStrings;
		this.delUserIds = delStrings;
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

	public void setAddUserIds(String addUserIds) {
		this.addUserIds = addUserIds;
	}

	public void setDelUserIds(String delUserIds) {
		this.delUserIds = delUserIds;
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