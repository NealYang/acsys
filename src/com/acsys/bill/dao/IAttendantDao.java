package com.acsys.bill.dao;

import com.acsys.bill.model.Attendant;

/**
 * @author Nealy
 * @date Aug 20, 2014
 */
public interface IAttendantDao {
	public Attendant getAttendantById(String id);

	public String addAttendant(Attendant attendant);

	public void updateAttendant(Attendant attendant);
}