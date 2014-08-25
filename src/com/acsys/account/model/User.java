package com.acsys.account.model;

import java.util.Date;

/**
 * @author Nealy
 * @date Jul 25, 2014
 */
public class User {
	private String id;
	private String name = "";
	private String email = "";
	private String password = "";
	private double amount = 0;
	private String groupingIds;
	private Date created;
	private Date modified;
	private Date lastTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getGroupingIds() {
		return groupingIds;
	}

	public void setGroupingIds(String groupingIds) {
		this.groupingIds = groupingIds;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
}