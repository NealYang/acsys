package com.acsys.bill.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.acsys.bill.dao.IBillDao;
import com.acsys.bill.model.Bill;

/**
 * @author Nealy
 * @date Aug 19, 2014
 */
@Repository
public class BillDao implements IBillDao {
	@Resource
	private SqlSession sqlSession;

	public Bill getBillById(String id) {
		Bill bill = sqlSession.selectOne("getBillById", id);
		return bill;
	}

	public List<Bill> getBillsByGroupingId(String groupingId) {
		List<Bill> list = sqlSession.selectList("getBillsByGroupingId", groupingId);
		return list;
	}

	public List<Bill> getAllBills() {
		List<Bill> list = sqlSession.selectList("getAllBills");
		return list;
	}

	public String addBillBase(Bill bill) {
		sqlSession.insert("addBill", bill);
		return bill.getId();
	}

	public void updateBillBase(Bill bill) {
		sqlSession.update("updateBill", bill);
	}
}