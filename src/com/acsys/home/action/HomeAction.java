package com.acsys.home.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.acsys.account.model.User;
import com.acsys.account.service.IUserService;
import com.acsys.bill.model.Bill;
import com.acsys.bill.service.IBillService;
import com.acsys.common.Utils;
import com.acsys.core.CommonContext;
import com.acsys.core.base.action.BaseAction;
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
	@Resource
	private IBillService billService;

	@Override
	public String execute() {
		getAllGroupings();
		if (groupings != null && groupings.size() > 0) {
			User currentUser = CommonContext.getCurrentUser();
			if (Utils.isEmpty(groupingId) && !Utils.isEmpty(currentUser)) {
				String groupingIds = currentUser.getGroupingIds();
				if (!Utils.isEmpty(groupingIds)) {
					groupingId = groupingIds.substring(0, groupingIds.indexOf(","));
				}
			}
			if (Utils.isEmpty(groupingId)) {
				groupingId = groupings.get(0).getId();
			}
		}
		getUsersForGrouping();
		if (!Utils.isEmpty(groupingId)) {
			getBillsByGroupingId();
		} else {
			getAllBills();
		}
		return SUCCESS;
	}

	private void getAllGroupings() {
		groupings = groupingService.getAllGroupings();
	}

	private void getUsersForGrouping() {
		users = userService.getUsersForGrouping(groupingId);
	}

	private void getBillsByGroupingId() {
		bills = billService.getBillsByGroupingId(groupingId);
	}

	private void getAllBills() {
		bills = billService.getAllBills();
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

	public List<Bill> getBills() {
		return bills;
	}
}