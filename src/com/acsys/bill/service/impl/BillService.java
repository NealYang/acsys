package com.acsys.bill.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.acsys.account.model.User;
import com.acsys.account.service.IUserService;
import com.acsys.bill.dao.IAttendantDao;
import com.acsys.bill.dao.IBillDao;
import com.acsys.bill.model.Attendant;
import com.acsys.bill.model.Bill;
import com.acsys.bill.service.IBillService;
import com.acsys.common.Utils;
import com.acsys.core.base.service.BaseService;

/**
 * @author Nealy
 * @date Aug 20, 2014
 */
@Service
public class BillService extends BaseService implements IBillService {
	@Resource
	private IBillDao billDao;
	@Resource
	private IAttendantDao attendantDao;
	@Resource
	private IUserService userService;

	public Bill getBillById(String id) throws Exception {
		if (Utils.isEmpty(id)) {
			return null;
		}
		return billDao.getBillById(id);
	}

	public List<Bill> getBillsByGroupingId(String groupingId) {
		if (Utils.isEmpty(groupingId)) {
			return new ArrayList<Bill>();
		}
		return billDao.getBillsByGroupingId(groupingId);
	}

	public List<Bill> getAllBills() {
		List<Bill> list = billDao.getAllBills();
		return list;
	}

	public String addBill(Bill bill) throws Exception {
		if (Utils.isEmpty(bill)) {
			return "";
		}

		bill.setId(null);
		bill.setCreated(new Date());
		bill.setLastTime(new Date());
		String billId = billDao.addBillBase(bill);
		if (Utils.isEmpty(billId)) {
			return "";
		}

		String payUserId = bill.getPayUserId();
		User payUser = userService.getUserById(payUserId);
		if (!Utils.isEmpty(payUser)) {
			double amount = payUser.getAmount();
			amount -= bill.getAmount();
			payUser.setAmount(amount);
			userService.updateUser(payUser);
		}

		List<Attendant> attendants = bill.getAttendants();
		for (Attendant attendant : attendants) {
			addAttendant(attendant, billId);
		}

		return billId;
	}

	public void updateBill(Bill bill, String[] delAttendantIds) throws Exception {
		if (Utils.isEmpty(bill) || Utils.isEmpty(bill.getId())) {
			log.warn("Bill update abort. Param:bill=" + String.valueOf(bill) + " delAttendantIds=" + delAttendantIds);
			return;
		}

		Bill oldBill = this.getBillById(bill.getId());

		bill.setModified(new Date());
		bill.setLastTime(new Date());
		billDao.updateBillBase(bill);

		String payUserId = bill.getPayUserId();
		User payUser = userService.getUserById(payUserId);
		if (!Utils.isEmpty(payUser)) {
			double amount = payUser.getAmount();
			amount += oldBill.getAmount();
			amount -= bill.getAmount();
			payUser.setAmount(amount);
			userService.updateUser(payUser);
		}

		List<Attendant> attendants = bill.getAttendants();
		for (Attendant attendant : attendants) {
			if (Utils.isEmpty(attendant.getId())) {
				addAttendant(attendant, bill.getId());
			} else {
				delAttendant(attendant.getId());
				addAttendant(attendant, bill.getId());
			}
		}

		if (!Utils.isEmpty(delAttendantIds)) {
			for (String id : delAttendantIds) {
				delAttendant(id);
			}
		}
	}

	public void delBill(String billId) throws Exception {
		if (Utils.isEmpty(billId)) {
			log.warn("Bill del abort. Param:billId=" + billId);
			return;
		}

		Bill bill = this.getBillById(billId);
		if (Utils.isEmpty(billId)) {
			log.warn("Bill is null. Param:billId=" + billId);
			return;
		}

		List<Attendant> attendants = bill.getAttendants();
		for (Attendant attendant : attendants) {
			this.delAttendant(attendant.getId());
		}
		bill.setDelFlag("1");
		bill.setModified(new Date());
		bill.setLastTime(new Date());
		this.updateBill(bill, null);
	}

	public String addAttendant(Attendant attendant, String billId) {
		if (Utils.isEmpty(attendant)) {
			log.warn("Attendant adding abort. Param:attendant=" + attendant + " billId=" + billId);
			return "";
		}
		if (Utils.isEmpty(billId)) {
			log.warn("Cannnot add an attendant without billId. Param:attendant=" + attendant + " billId=" + billId);
			return "";
		}
		if (Utils.isEmpty(attendant.getUserId())) {
			log.warn("Cannnot add an attendant without userId. Param:attendant=" + attendant + " billId=" + billId);
			return "";
		}

		User user = null;
		try {
			user = userService.getUserById(attendant.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		if (Utils.isEmpty(user)) {
			log.warn("Cannnot add an attendant with nonexistent user. Param:attendant=" + attendant + " billId=" + billId);
			return "";
		}

		double amount = user.getAmount();
		amount += attendant.getAmount();
		user.setAmount(amount);
		userService.updateUser(user);

		attendant.setId(null);
		attendant.setBillId(billId);
		attendant.setDelFlag("0");
		return attendantDao.addAttendant(attendant);
	}

	public void delAttendant(String attendantId) {
		if (Utils.isEmpty(attendantId)) {
			log.warn("Param attendantId is required. Param:attendantId=" + attendantId);
			return;
		}

		Attendant attendant = attendantDao.getAttendantById(attendantId);
		if (Utils.isEmpty(attendant)) {
			log.warn("Attendant doesn't exist. Param:attendantId=" + attendantId);
			return;
		}
		User user = null;
		try {
			user = userService.getUserById(attendant.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		if (Utils.isEmpty(user)) {
			log.warn("User doesn't exist. Param:attendant=" + attendant);
			return;
		}

		double amount = user.getAmount();
		amount -= attendant.getAmount();
		user.setAmount(amount);
		userService.updateUser(user);

		attendant.setDelFlag("1");
		attendantDao.updateAttendant(attendant);
	}
}