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
	private String billId;
	private Bill bill;
	private String groupingId;
	private List<Grouping> groupings = new ArrayList<Grouping>();
	private List<User> users = new ArrayList<User>();

	@Resource
	private IGroupingService groupingService;
	@Resource
	private IUserService userService;
	@Resource
	private IBillService billService;

	@Override
	public String input() {
		getAllGroupings();

		if (!Utils.isEmpty(billId)) {
			try {
				bill = billService.getBillById(billId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (groupings != null && groupings.size() > 0) {
			if (!Utils.isEmpty(bill)) {
				groupingId = bill.getGroupingId();
			} else if (Utils.isEmpty(groupingId)) {
				groupingId = groupings.get(0).getId();
			}
		}
		getUsersForGrouping();
		return INPUT;
	}

	public String submit() throws Exception {
		if (Utils.isEmpty(bill.getId())) {
			String ipAddr = this.request.getRemoteAddr();
			bill.setIpAddr(ipAddr);
			billService.addBill(bill);
		} else {
			// billService.updateBill(bill, delAttendantIds, attendants);
		}
		return SUCCESS;
	}

	private void getAllGroupings() {
		groupings = groupingService.getAllGroupings();
	}

	private void getUsersForGrouping() {
		users = userService.getUsersForGrouping(groupingId);
	}

	public void setBillId(String billId) {
		this.billId = billId;
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
}