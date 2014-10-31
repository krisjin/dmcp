package com.hx.dmcp.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hx.dmcp.constant.SystemConstant;
import com.hx.dmcp.entity.User;
import com.hx.dmcp.exception.ControllerValidateException;
import com.hx.dmcp.service.UserService;
import com.hx.dmcp.util.JsonUtil;

/**
 * @author krisjin
 */
@Controller
public class BaseController {

	public final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected UserService userService;

	protected <T> void validate(JsonUtil<T> json) throws ControllerValidateException {
		if (json.getErrors().size() > 0) {
			json.setResult(false);
			throw new ControllerValidateException("有错误发生");
		} else {
			json.setResult(true);
		}
	}

	protected User getUser(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(SystemConstant.USER_SESSION);
		return user;
	}
}
