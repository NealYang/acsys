package com.acsys.bill.dao;

import java.util.List;

import com.acsys.bill.model.Bill;

/**
 * @author Nealy
 * @date Aug 19, 2014
 */
public interface IBillDao {
	public Bill getBillById(String id) throws Exception;

	public List<Bill> getBillsByGroupingId(String groupingId);

	public List<Bill> getAllBills();

	public String addBillBase(Bill bill);

	public void updateBillBase(Bill bill);
}