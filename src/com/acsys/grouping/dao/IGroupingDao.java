package com.acsys.grouping.dao;

import java.util.List;

import com.acsys.grouping.model.Grouping;

/**
 * @author Nealy
 * @date Aug 18, 2014
 */
public interface IGroupingDao {
	public Grouping getGroupingById(String id);

	public List<Grouping> getAllGroupings();

	public String addGrouping(Grouping grouping);

	public void updateGrouping(Grouping grouping);
}