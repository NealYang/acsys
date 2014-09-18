package com.acsys.core.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * @author Nealy
 * @date Sep 18, 2014
 */
@Repository
public class BaseDao {
	public final Logger log = LogManager.getLogger(getClass().getName());
}