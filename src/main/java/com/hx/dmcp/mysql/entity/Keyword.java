package com.hx.dmcp.mysql.entity;

/**
 * @author krisjin
 * @date 2014-11-21
 */
public class Keyword {

	private int id;

	private String name;

	private int status;

	private int weights;

	private String value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getWeights() {
		return weights;
	}

	public void setWeights(int weights) {
		this.weights = weights;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
