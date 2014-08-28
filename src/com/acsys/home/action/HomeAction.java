package com.acsys.home.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.acsys.account.model.User;
import com.acsys.account.service.IUserService;
import com.acsys.bill.model.Bill;
import com.acsys.core.BaseAction;
import com.acsys.grouping.model.Grouping;
import com.acsys.grouping.service.IGroupingService;

/**
 * @author Nealy
 * @date Jul 28, 2014
 */
public class HomeAction extends BaseAction {
	private String groupingId;
	private List<Grouping> groupings = new ArrayList<Grouping>();
	private List<User> users = new ArrayList<User>();
	private List<Bill> bills = new ArrayList<Bill>();

	@Resource
	private IGroupingService groupingService;
	@Resource
	private IUserService userService;

	@Override
	public String execute() {
		getAllGroupings();
		if (groupings != null && groupings.size() > 0) {
			groupingId = groupings.get(0).getId();
		}
		getUsersForGrouping();
		getAllBills();
		return SUCCESS;
	}

	private void getAllGroupings() {
		groupings = groupingService.getAllGroupings();
	}

	private void getUsersForGrouping() {
		users = userService.getUsersForGrouping(groupingId);
	}

	private void getAllBills() {
	}

	public void setGroupingId(String groupingId) {
		this.groupingId = groupingId;
	}

	public List<Grouping> getGroupings() {
		return groupings;
	}

	public List<User> getUsers() {
		return users;
	}

	public List<Bill> getBills() {
		return bills;
	}
}