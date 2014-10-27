package com.hx.dmcp.controller;

import com.hx.dmcp.constant.SystemConstant;
import com.hx.dmcp.constant.ValidateInfoConstant;
import com.hx.dmcp.controller.admin.BaseController;
import com.hx.dmcp.entity.User;
import com.hx.dmcp.entity.vo.JsonVo;
import com.hx.dmcp.service.UserService;
import com.hx.dmcp.util.HttpUtils;
import com.hx.dmcp.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/auth/admin")
public class LoginController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public String adminLogin(HttpServletRequest request, ModelMap modelMap) {
		return "page/login";
	}

	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String adminLogout(HttpServletRequest request, ModelMap modelMap, HttpServletResponse response) {
		request.getSession().removeAttribute(SystemConstant.USER_SESSION);
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
	public JsonVo<String> userLogin(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password,
			HttpServletRequest request, ModelMap modelMap) {
		HttpSession session = request.getSession();
		JsonVo<String> json = new JsonVo<String>();
		try {
			if (EmailValidator.getInstance().isValid(email)) {
				User u = userService.getUserByEmail(email);
				if (null == u) {
					json.getErrors().put("email", ValidateInfoConstant.USER_EMAIL);
				}
			} else {
				if (email == null || email.equals("")) {
					json.getErrors().put("email", ValidateInfoConstant.USER_EMAIL);
				} else {
					User u = userService.getUserByName(email);
					if (u == null) {
						json.getErrors().put("email", ValidateInfoConstant.USER_EMAIL);
					}
				}
			}

			if (StringUtils.isBlank(password)) {
				json.getErrors().put("password", ValidateInfoConstant.PASSWORD);
			}

			User u = userService.getUserByPassword(MD5Util.encrypt(password));
			if (null == u) {
				json.getErrors().put("password", ValidateInfoConstant.PASSWORD);
			}

			if (!u.getPassword().equals(MD5Util.encrypt(password))) {
				json.getErrors().put("password", ValidateInfoConstant.PASSWORD);
			}

			session.setAttribute(SystemConstant.USER_SESSION, u);
			json.setResult(true);
		} catch (Exception e) {
			json.setResult(false);
		}
		return json;
	}
}
