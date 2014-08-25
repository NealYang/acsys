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

	public String addGrouping(String name, String remark);

	public int updateGroupingUser(String groupingId, String[] delUserIds, String[] addUserIds);

	public void updateGroupingBase(String groupingId, String groupingName, String remark, int userNum);

	public void updateGrouping(String groupingId, String groupingName, String remark, String[] delUserIds, String[] addUserIds);
}