package com.acsys.bill.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.acsys.account.model.User;
import com.acsys.account.service.IUserService;
import com.acsys.bill.model.Bill;
import com.acsys.common.Utils;
import com.acsys.core.BaseAction;
import com.acsys.grouping.model.Grouping;
import com.acsys.grouping.service.IGroupingService;

/**
 * @author Nealy
 * @date Jul 30, 2014
 */
public class BillAction extends BaseAction {
	private String groupingId;
	private List<Grouping> groupings = new ArrayList<Grouping>();
	private List<User> users = new ArrayList<User>();
	private Bill bill;

	@Resource
	private IGroupingService groupingService;
	@Resource
	private IUserService userService;

	@Override
	public String input() {
		getAllGroupings();
		if (groupings != null && groupings.size() > 0) {
			if (Utils.isEmpty(groupingId)) {
				groupingId = groupings.get(0).getId();
			}
		}
		getUsersForGrouping();
		return INPUT;
	}

	public String submit() {
		return INPUT;
	}

	private void getAllGroupings() {
		groupings = groupingService.getAllGroupings();
	}

	private void getUsersForGrouping() {
		users = userService.getUsersForGrouping(groupingId);
	}

	public void setGroupingId(String groupingId) {
		this.groupingId = groupingId;
	}

	public String getGroupingId() {
		return groupingId;
	}

	public List<Grouping> getGroupings() {
		return groupings;
	}

	public List<User> getUsers() {
		return users;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}
}