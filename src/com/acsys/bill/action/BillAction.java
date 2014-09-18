package com.acsys.bill.action;

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
 * @date Jul 30, 2014
 */
public class BillAction extends BaseAction {
	private String billId;
	private String delAttendantIds;// xxx,xxx,xxx,
	private Bill bill;
	private String groupingId;
	private List<Grouping> groupings = new ArrayList<Grouping>();
	private List<User> users = new ArrayList<User>();

	private String backParam = "";

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
			}
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
		return INPUT;
	}

	public String submit() throws Exception {
		String idString = "";
		if (Utils.isEmpty(bill)) {
			return this.input();
		}
		if (Utils.isEmpty(bill.getId())) {
			String ipAddr = this.request.getRemoteAddr();
			bill.setIpAddr(ipAddr);
			idString = billService.addBill(bill);
		} else {
			idString = bill.getId();
			String ipAddr = this.request.getRemoteAddr();
			bill.setIpAddr(ipAddr);
			trimAttendantIds();
			String[] dels = Utils.isEmpty(delAttendantIds) ? null : delAttendantIds.split(",");
			billService.updateBill(bill, dels);
		}
		backParam += this.request.getServletPath() + "?billId=" + idString;
		return "directURL";
	}

	public String delBill() throws Exception {
		if (Utils.isEmpty(billId)) {
			return "json";
		}
		billService.delBill(billId);
		backParam = "complete";
		return "json";
	}

	private void getAllGroupings() {
		groupings = groupingService.getAllGroupings();
	}

	private void getUsersForGrouping() {
		users = userService.getUsersForGrouping(groupingId);
	}

	private void trimAttendantIds() {
		String[] dels = Utils.isEmpty(delAttendantIds) ? null : delAttendantIds.split(",");
		String delStrings = "";
		if (!Utils.isEmpty(dels)) {
			for (String string : dels) {
				if (!Utils.isEmpty(delStrings)) {
					delStrings += "," + string.trim();
				} else {
					delStrings += string.trim();
				}
			}
		}
		this.delAttendantIds = delStrings;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public void setDelAttendantIds(String delAttendantIds) {
		this.delAttendantIds = delAttendantIds;
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

	public String getBackParam() {
		return backParam;
	}
}