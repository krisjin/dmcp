package com.hx.dmcp.dao;

import java.util.List;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hx.dmcp.entity.Visa;

/**
 * @author krisjin (mailto:krisjin86@163.com)
 */
@Repository
public interface VisaDao {

	public void addVisa(Visa visa);

	public void updateVisa(Visa visa);

	public void deleteVisa(@Param("id") long id);

	public List<Visa> getVisaWithPage(@Param("offset") long offset, @Param("rows") long rows);

	public Visa getVisaById(@Param("id") long id);

	public int getAllCounts();

	public List<Visa> getVisaes();

	public List<Visa> getVisaByName(@Param("title") String title);

	public List<Visa> getAllVisa();
}
