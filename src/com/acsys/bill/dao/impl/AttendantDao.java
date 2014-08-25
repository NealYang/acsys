package com.acsys.bill.dao.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.acsys.bill.dao.IAttendantDao;
import com.acsys.bill.model.Attendant;

/**
 * @author Nealy
 * @date Aug 20, 2014
 */
@Repository
public class AttendantDao implements IAttendantDao {
	@Resource
	SqlSession sqlSession;

	public Attendant getAttendantById(String id) {
		return sqlSession.selectOne("getAttendantById", id);
	}

	public String addAttendant(Attendant attendant) {
		sqlSession.insert("addAttendant", attendant);
		return attendant.getId();
	}

	public void updateAttendant(Attendant attendant) {
		sqlSession.update("updateAttendant", attendant);
	}

}