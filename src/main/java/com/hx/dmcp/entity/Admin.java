package com.hx.dmcp.entity;

import java.util.Date;

import com.hx.dmcp.constant.AdminConstant;

/**
 * @author krisjin
 */
public class Admin {

	private long adminId;

	private String email;

	private String name;

	private String password;

	private AdminConstant.Status status;

	private Date createTime;

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AdminConstant.Status getStatus() {
		return status;
	}

	public void setStatus(AdminConstant.Status status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
