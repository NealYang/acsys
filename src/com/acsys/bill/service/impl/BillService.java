package com.acsys.bill.service.impl;

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

/**
 * @author Nealy
 * @date Aug 20, 2014
 */
@Service
public class BillService implements IBillService {
	@Resource
	private IBillDao billDao;
	@Resource
	private IAttendantDao attendantDao;
	@Resource
	private IUserService userService;

	public Bill getBillById(String id) {
		if (Utils.isEmpty(id)) {
			return null;
		}
		return billDao.getBillById(id);
	}

	public List<Bill> getBillsByGroupingId(String groupingId) {
		if (Utils.isEmpty(groupingId)) {
			return null;
		}
		return billDao.getBillsByGroupingId(groupingId);
	}

	public List<Bill> getAllBills() {
		List<Bill> list = billDao.getAllBills();
		return list;
	}

	public String addBill(Bill bill, List<Attendant> attendants) {
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
		for (Attendant attendant : attendants) {
			String userId = attendant.getUserId();
			if (Utils.isEmpty(userId)) {
				continue;
			} else {
				User user = userService.getUserById(userId);
				if (Utils.isEmpty(user)) {
					continue;
				} else {
					double amount = user.getAmount();
					amount += attendant.getAmount();
					user.setAmount(amount);
					userService.updateUser(user);
				}
			}
			attendant.setId(null);
			attendant.setBillId(billId);
			attendantDao.addAttendant(attendant);
		}

		return billId;
	}

	public void updateBill(Bill bill, String[] delAttendantIds, List<Attendant> addAttendants) {
		if (Utils.isEmpty(bill)) {
			return;
		}

		bill.setModified(new Date());
		bill.setLastTime(new Date());
		billDao.updateBillBase(bill);
		for (String id : delAttendantIds) {
			Attendant attendant = attendantDao.getAttendantById(id);
			if (!Utils.isEmpty(attendant)) {
				String userId = attendant.getUserId();
				if (Utils.isEmpty(userId)) {
					continue;
				} else {
					User user = userService.getUserById(userId);
					if (Utils.isEmpty(user)) {
						continue;
					} else {
						double amount = user.getAmount();
						amount -= attendant.getAmount();
						user.setAmount(amount);
						userService.updateUser(user);
					}
				}
				attendant.setDelFlag("1");
				attendantDao.updateAttendant(attendant);
			}
		}
		for (Attendant attendant : addAttendants) {
			String userId = attendant.getUserId();
			if (Utils.isEmpty(userId)) {
				continue;
			} else {
				User user = userService.getUserById(userId);
				if (Utils.isEmpty(user)) {
					continue;
				} else {
					double amount = user.getAmount();
					amount += attendant.getAmount();
					user.setAmount(amount);
					userService.updateUser(user);
				}
			}
			attendant.setId(null);
			attendantDao.addAttendant(attendant);
		}
	}
}