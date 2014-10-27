package com.hx.dmcp.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hx.dmcp.constant.AdminConstant;
import com.hx.dmcp.entity.User;
import com.hx.dmcp.entity.vo.JsonVo;

/**
 * @author krisjin
 * @date 2014-10-24
 */
@Controller
@RequestMapping("/admin/user")
public class UserManageController extends BaseController {

	@RequestMapping(value = "/update.htm", method = RequestMethod.GET)
	public String oneAdmin(
			@RequestParam(value = "userId", defaultValue = "0") long userId,
			ModelMap modelMap, HttpServletRequest request) {
			User user = new User();
			if (userId == 0) {
				user = this.getUser(request);
			}
			modelMap.put("user", user);
			return "page/userModify";
	}
	
	@ResponseBody
	@RequestMapping(value = "/update.json", method = RequestMethod.POST)
	public JsonVo<String> updateAdmin(
			@RequestParam(value = "userName") String userName, 
			@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password, 
			@RequestParam(value = "userId") long userId,
			@RequestParam(value = "status") AdminConstant.Status status) {

		JsonVo<String> json = new JsonVo<String>();
		User user = userService.getUserById(userId);
		try {
			
			if (userName.equals("")) 
				json.getErrors().put("userName", "用户名称不能为空");
			
			if(password.equals("")|| password ==null)
				json.getErrors().put("password", "密码不能为空");
			
			if (email.equals(""))
				json.getErrors().put("email", "电子邮箱不能为空");
				
			validate(json);
			user = userService.updateUser(userId, userName, password, status, email);
			json.setResult(true);
		} catch (Exception e) {
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}

}
