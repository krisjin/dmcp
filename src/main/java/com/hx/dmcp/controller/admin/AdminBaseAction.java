package com.hx.dmcp.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hx.dmcp.constant.SystemConstant;
import com.hx.dmcp.entity.Admin;
import com.hx.dmcp.entity.vo.JsonVo;
import com.hx.dmcp.exception.ControllerValidateException;
import com.hx.dmcp.service.AdminService;
import com.hx.dmcp.util.UpdatePictureUtils;

/**
 * @author krisjin
 */
@Controller
public class AdminBaseAction {

	public final Logger logger = LoggerFactory.getLogger(this.getClass());


	@Autowired
	protected AdminService adminService;

	@Autowired
	protected UpdatePictureUtils updatePictureConsTant;

	protected <T> void validate(JsonVo<T> json) throws ControllerValidateException {
		if (json.getErrors().size() > 0) {
			json.setResult(false);
			throw new ControllerValidateException("有错误发生");
		} else {
			json.setResult(true);
		}
	}

	protected Admin getAdmin(HttpServletRequest request) {
		Admin admin = (Admin) request.getSession().getAttribute(SystemConstant.SESSION_ADMIN);
		return admin;
	}
}
