package com.hx.dmcp.entity;

import java.util.Date;

/**
 * @author krisjin
 * @date 2014-10-24
 */
public class Visa {

	private long id;
	private String title;
	private int visaType;
	private String handleDate;
	private String cost;
	private String costDescription;
	private String acceptPeople;
	private String content;
	private String avatarUrl;
	private Date createDate;
	private long category;
	private String categoryName;
	private String visaTypeName;

	public String getVisaTypeName() {
		return visaTypeName;
	}

	public void setVisaTypeName(String visaTypeName) {
		this.visaTypeName = visaTypeName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getVisaType() {
		return visaType;
	}

	public void setVisaType(int visaType) {
		this.visaType = visaType;
	}

	public String getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(String handleDate) {
		this.handleDate = handleDate;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getCostDescription() {
		return costDescription;
	}

	public void setCostDescription(String costDescription) {
		this.costDescription = costDescription;
	}

	public String getAcceptPeople() {
		return acceptPeople;
	}

	public void setAcceptPeople(String acceptPeople) {
		this.acceptPeople = acceptPeople;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public long getCategory() {
		return category;
	}

	public void setCategory(long category) {
		this.category = category;
	}

}
