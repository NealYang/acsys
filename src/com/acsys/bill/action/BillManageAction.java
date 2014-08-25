package com.acsys.bill.action;

import java.util.List;

import javax.annotation.Resource;

import com.acsys.bill.model.Attendant;
import com.acsys.bill.model.Bill;
import com.acsys.bill.service.IBillService;
import com.acsys.common.Utils;
import com.acsys.core.BaseAction;

/**
 * @author Nealy
 * @date Aug 20, 2014
 */
public class BillManageAction extends BaseAction {
	private String billId;
	private String groupingId;
	private Bill bill;
	private List<Attendant> attendants;
	private String[] delAttendantIds;
	private List<Bill> bills;
	private boolean result = false;

	@Resource
	private IBillService billService;

	public String saveBill() {
		try {
			if (Utils.isEmpty(bill)) {
				result = false;
				return SUCCESS;
			}

			if (Utils.isEmpty(bill.getId())) {
				billService.addBill(bill, attendants);
			} else {
				billService.updateBill(bill, delAttendantIds, attendants);
			}
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return SUCCESS;
	}

	public String billById() {
		bill = billService.getBillById(billId);
		result = true;
		return SUCCESS;
	}

	public String billsByGroupingId() {
		bills = billService.getBillsByGroupingId(groupingId);
		result = true;
		return SUCCESS;
	}

	public String allBills() {
		bills = billService.getAllBills();
		result = true;
		return SUCCESS;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public void setGroupingId(String groupingId) {
		this.groupingId = groupingId;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Bill getBill() {
		return bill;
	}

	public void setAttendants(List<Attendant> attendants) {
		this.attendants = attendants;
	}

	public void setDelAttendantIds(String[] delAttendantIds) {
		this.delAttendantIds = delAttendantIds;
	}

	public List<Bill> getBills() {
		return bills;
	}

	public boolean isResult() {
		return result;
	}
}