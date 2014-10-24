package com.hx.dmcp.entity.vo;

import java.util.HashMap;


import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.hx.dmcp.exception.ControllerValidateException;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class JsonVo<T> {
	private boolean result;

	private String msg;

	private HashMap<String, String> errors = new HashMap<String, String>();

	private T t;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public HashMap<String, String> getErrors() {
		return errors;
	}

	public void setErrors(HashMap<String, String> errors) {
		this.errors = errors;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public void check() throws ControllerValidateException {
		if (this.getErrors().size() > 0) {
			this.setResult(false);
			throw new ControllerValidateException("有错误发生");
		} else {
			this.setResult(true);
		}
	}
}
