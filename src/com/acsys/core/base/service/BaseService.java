package com.acsys.core.base.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author Nealy
 * @date Sep 18, 2014
 */
@Service
public class BaseService {
	public final Logger log = LogManager.getLogger(getClass().getName());
}