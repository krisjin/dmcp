package com.hx.dmcp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hx.dmcp.entity.Admin;
import com.hx.dmcp.entity.vo.AdminVo;

/**
 * TODO 此处填写 class 信息
 * 
 * @author krisjin
 */
@Repository
public interface AdminDao {

	public int addAdmin(Admin admin);

	public int deleteAdmin(@Param("adminId") long adminId);

	public int updateAdmin(Admin admin);

	public List<Admin> getAllList(@Param("offset") long offset, @Param("rows") long rows);

	public int getAllListCount();

	public Admin getAdminById(@Param("adminId") long adminId);

	public AdminVo getAdminByEmail(@Param("email") String email);

}
