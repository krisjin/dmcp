package com.hx.dmcp.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hx.dmcp.constant.AdminConstant;
import com.hx.dmcp.entity.Admin;
import com.hx.dmcp.entity.vo.JsonVo;

/**
 * @author krisjin
 * @date 2014-10-24
 */
@Controller
@RequestMapping("/admin/admin")
public class AdminAction extends AdminBaseAction {

	@RequestMapping(value = "/update.htm", method = RequestMethod.GET)
	public String oneAdmin(
			@RequestParam(value = "adminId", defaultValue = "0") long adminId,
			ModelMap modelMap, HttpServletRequest request) {
			Admin admin = new Admin();
			if (adminId == 0) {
				admin = this.getAdmin(request);
				admin = adminService.getAdminById(admin.getAdminId());
			} else {
				admin = adminService.getAdminById(adminId);
			}
			modelMap.put("admin", admin);
			return "page/userModify";
	}
	
	@ResponseBody
	@RequestMapping(value = "/update.json", method = RequestMethod.POST)
	public JsonVo<String> updateAdmin(
			@RequestParam(value = "adminName") String adminName, 
			@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password, 
			@RequestParam(value = "adminId") long adminId,
			@RequestParam(value = "status") AdminConstant.Status status) {

		JsonVo<String> json = new JsonVo<String>();
		Admin admin = adminService.getAdminByEmail(email);
		try {
			
			if (adminName.equals("")) 
				json.getErrors().put("adminName", "管理员名称不能为空");
			
			if(password.equals("")|| password ==null)
				json.getErrors().put("password", "密码不能为空");
			
			if (email.equals(""))
				json.getErrors().put("email", "电子邮箱不能为空");
				
			validate(json);
			admin=adminService.updateAdmin(adminId, adminName, password, status,email);
			json.setResult(true);
		} catch (Exception e) {
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}

}
