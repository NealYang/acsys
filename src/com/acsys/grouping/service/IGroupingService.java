package com.acsys.grouping.service;

import java.util.List;

import com.acsys.grouping.model.Grouping;

/**
 * @author Nealy
 * @date Aug 18, 2014
 */
public interface IGroupingService {
	public Grouping getGroupingById(String id);

	public List<Grouping> getAllGroupings();

	public String addGrouping(String name, String remark, String[] addUserIds);

	public int updateGroupingUser(Grouping grouping, String[] delUserIds, String[] addUserIds);

	public void updateGroupingBase(Grouping grouping, int userNum);

	public void updateGrouping(Grouping grouping, String[] delUserIds, String[] addUserIds);

	public void deleteGrouping(String groupingId);
}