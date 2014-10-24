package com.hx.dmcp.dao;

import java.util.List;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hx.dmcp.entity.VisaItem;

/**
 * TODO 此处填写 class 信息
 * 
 * @author krisjin
 * @date 2014-5-28上午11:11:39
 */
@Repository
public interface VisaItemDao {

	public void addVisaItem(VisaItem visaItem);

	public void updateVisaItem(VisaItem visaItem);

	public void deleteVisaItem(@Param("id") long id);

	public List<VisaItem> getVisaItem(@Param("offset") long offset, @Param("rows") long rows);

	public VisaItem getVisaItemById(@Param("id") long id);

	public int getAllCounts();

	public List<VisaItem> getVisaItemByDisplay(@Param("offset") long offset, @Param("rows") long rows);

}
