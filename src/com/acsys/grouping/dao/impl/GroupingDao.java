package com.acsys.grouping.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.acsys.grouping.dao.IGroupingDao;
import com.acsys.grouping.model.Grouping;

/**
 * @author Nealy
 * @date Aug 18, 2014
 */
@Repository
public class GroupingDao implements IGroupingDao {
	@Resource
	private SqlSession sqlSession;

	public Grouping getGroupingById(String id) {
		Grouping grouping = sqlSession.selectOne("getGroupingById", id);
		return grouping;
	}

	public List<Grouping> getAllGroupings() {
		List<Grouping> groupings = sqlSession.selectList("getAllGroupings");
		return groupings;
	}

	public String addGrouping(Grouping grouping) {
		sqlSession.insert("addGrouping", grouping);
		return grouping.getId();
	}

	public void updateGrouping(Grouping grouping) {
		sqlSession.update("updateGrouping", grouping);
	}
}