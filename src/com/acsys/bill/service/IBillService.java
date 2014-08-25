package com.acsys.bill.service;

import java.util.List;

import com.acsys.bill.model.Attendant;
import com.acsys.bill.model.Bill;

/**
 * @author Nealy
 * @date Aug 20, 2014
 */
public interface IBillService {
	public Bill getBillById(String id);

	public List<Bill> getBillsByGroupingId(String groupingId);

	public List<Bill> getAllBills();

	public String addBill(Bill bill, List<Attendant> attendants);

	public void updateBill(Bill bill, String[] delAttendantIds, List<Attendant> addAttendants);
}