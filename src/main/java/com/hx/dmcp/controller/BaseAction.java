package com.hx.dmcp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hx.dmcp.entity.vo.JsonVo;
import com.hx.dmcp.exception.ControllerValidateException;

/**
 * @author krisjin
 */
public class BaseAction {

	protected final Logger logger =LoggerFactory.getLogger(this.getClass());

	protected <T> void validate(JsonVo<T> json) throws ControllerValidateException {
		if (json.getErrors().size() > 0) {
			json.setResult(false);
			throw new ControllerValidateException("有错误发生");
		} else {
			json.setResult(true);
		}
	}

}
