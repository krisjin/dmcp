package com.hx.dmcp.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hx.dmcp.constant.SystemConstant;
import com.hx.dmcp.constant.ValidateInfoConstant;
import com.hx.dmcp.entity.vo.JsonVo;
import com.hx.dmcp.service.AdminService;
import com.hx.dmcp.util.HttpUtils;

@Controller
@RequestMapping("/auth/admin")
public class LoginAction extends BaseAction {


	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public String adminLogin(HttpServletRequest request, ModelMap modelMap) {
		return "page/login";
	}

	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String adminLogout(HttpServletRequest request, ModelMap modelMap, HttpServletResponse response) {
		request.getSession().removeAttribute(SystemConstant.SESSION_ADMIN);
		String path = HttpUtils.getBasePath(request) + File.separator + "auth/admin/login.htm";

		try {
			response.sendRedirect(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/login.json", method = RequestMethod.POST)
	public JsonVo<String> adminLogin(@RequestParam(value = "email") String email, 
									 @RequestParam(value = "password") String password,
									 HttpServletRequest request, ModelMap modelMap) {
		
		JsonVo<String> json = new JsonVo<String>();

		try {
			if (!EmailValidator.getInstance().isValid(email)) {
				json.getErrors().put("email", ValidateInfoConstant.USER_EMAIL);
			}
			if (StringUtils.isBlank(password)) {
				json.getErrors().put("password", "密码不能为空");
			} else if (password.length() < 6 && password.length() > 30) {
				json.getErrors().put("password", "密码最少6个字符，最多30个字符");
			}
			this.validate(json);

			adminService.adminLogin(email, password, request);

		} catch (Exception e) {
			json.setResult(false);
			json.getErrors().put("password", ValidateInfoConstant.PASSWORD);
		}
		return json;
	}

}
