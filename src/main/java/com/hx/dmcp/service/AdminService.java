package com.hx.dmcp.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hx.dmcp.constant.AdminConstant;
import com.hx.dmcp.constant.SystemConstant;
import com.hx.dmcp.dao.AdminDao;
import com.hx.dmcp.entity.Admin;
import com.hx.dmcp.entity.vo.AdminVo;
import com.hx.dmcp.entity.vo.PageVo;
import com.hx.dmcp.util.EncryptUtils;

/**
 * TODO 此处填写 class 信息
 * 
 * @author krisjin
 */
@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;

	public Admin addAdmin(String email, String name, String password, AdminConstant.Status status){
		email = email.toLowerCase();
		Admin admin = new Admin();
		admin.setName(name);
		admin.setEmail(email);
		admin.setStatus(status);
		admin.setCreateTime(new Date());
		admin.setPassword(EncryptUtils.getEncrytPassword(password, email));
		adminDao.addAdmin(admin);
		return admin;
	}

	public int deleteAdmin(long adminId) {
		return adminDao.deleteAdmin(adminId);
	}

	/**
	 * @param adminId
	 * @param name
	 * @param password
	 * @param status
	 * @param email
	 * @return
	 * @throws AuthException
	 */
	public Admin updateAdmin(long adminId, String name, String password, AdminConstant.Status status, String email) {
		Admin admin = this.getAdminById(adminId);
		admin.setName(name);
		if (!StringUtils.isBlank(email)) {
			admin.setPassword(EncryptUtils.getEncrytPassword(password, email));
			admin.setEmail(email);
		} else {
			admin.setPassword(EncryptUtils.getEncrytPassword(password, admin.getEmail()));
		}
		admin.setStatus(status);
		adminDao.updateAdmin(admin);
		return admin;
	}

	public void adminLogin(String email, String password, HttpServletRequest request) {
		AdminVo admin = adminDao.getAdminByEmail(email);
		if (admin == null) {
		}
		admin.setFaceUrl(EncryptUtils.getFaceUrl(admin.getEmail()));
		String loginPassword = EncryptUtils.getEncrytPassword(password, email);
		if (loginPassword.equals(admin.getPassword())) {
			HttpSession session = request.getSession();
			admin.setPassword("");
			session.setAttribute(SystemConstant.SESSION_ADMIN, admin);
		} else {
		}
	}

	public Admin getAdminById(long adminId) {
		return adminDao.getAdminById(adminId);
	}

	public List<Admin> getAllList(long offset, long rows) {
		return adminDao.getAllList(offset, rows);
	}

	public int getAllListCount() {
		return adminDao.getAllListCount();
	}

	public PageVo<Admin> getAllListPage(int pageNum) {
		PageVo<Admin> pageVo = new PageVo<Admin>(pageNum);
		pageVo.setRows(5);
		List<Admin> list = this.getAllList(pageVo.getOffset(), pageVo.getRows());
		pageVo.setList(list);
		pageVo.setCount(this.getAllListCount());
		return pageVo;
	}

	public Admin getAdminByEmail(String email) {
		return adminDao.getAdminByEmail(email);
	}
}
