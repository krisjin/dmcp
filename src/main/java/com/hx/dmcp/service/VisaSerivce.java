package com.hx.dmcp.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hx.dmcp.dao.VisaDao;
import com.hx.dmcp.entity.Visa;
import com.hx.dmcp.entity.vo.PageVo;

/**
 * @author krisjin
 */

@Service
public class VisaSerivce {

	@Autowired
	private VisaDao visaDao;

	public void addVisa(Visa visa) {
		visaDao.addVisa(visa);
	}

	public void updateVisa(Visa visa) {
		visaDao.updateVisa(visa);
	}

	public void deleteVisa(long id) {
		visaDao.deleteVisa(id);
	}

	public PageVo<Visa> getVisaWithPage(int pageNum) {
		PageVo<Visa> page = new PageVo<Visa>(pageNum);
		page.setRows(200);

		List<Visa> items = visaDao.getVisaWithPage(page.getOffset(), page.getRows());
		page.setList(items);
		return page;
	}

	public Visa getVisaById(long id) {
		return visaDao.getVisaById(id);
	}

	public List<Visa> getVisaes() {
		return this.visaDao.getVisaes();
	}

	public List<Visa> getVisaByName(String title) {
		return visaDao.getVisaByName(title);
	}
}
