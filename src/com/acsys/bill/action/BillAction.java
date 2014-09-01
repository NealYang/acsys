package com.acsys.bill.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.acsys.account.model.User;
import com.acsys.account.service.IUserService;
import com.acsys.bill.model.Bill;
import com.acsys.bill.service.IBillService;
import com.acsys.common.Utils;
import com.acsys.core.BaseAction;
import com.acsys.grouping.model.Grouping;
import com.acsys.grouping.service.IGroupingService;

/**
 * @author Nealy
 * @date Jul 30, 2014
 */
public class BillAction extends BaseAction {
	private Bill bill;
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
		if (Utils.isEmpty(bill.getId())) {
			String ipAddr = this.request.getRemoteAddr();
			bill.setIpAddr(ipAddr);
			billService.addBill(bill);
		} else {
			// billService.updateBill(bill, delAttendantIds, attendants);
		}
		return SUCCESS;
	}

	public String billById() {
		bill = billService.getBillById(bill.getId());
		return INPUT;
	}

	public String billsByGroupingId() {
		bills = billService.getBillsByGroupingId(groupingId);
		return SUCCESS;
	}

	private void getAllGroupings() {
		groupings = groupingService.getAllGroupings();
	}

	private void getUsersForGrouping() {
		users = userService.getUsersForGrouping(groupingId);
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
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