package com.hx.dmcp.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hx.dmcp.dao.VisaItemDao;
import com.hx.dmcp.entity.VisaItem;
import com.hx.dmcp.entity.vo.PageVo;

@Service
public class VisaItemService {

	@Autowired
	private VisaItemDao visaItemDao;

	public void addVisaItem(VisaItem visaItem) {
		visaItemDao.addVisaItem(visaItem);
	}

	public PageVo<VisaItem> getVisaItemWithPage(int pageNum) {
		PageVo<VisaItem> page = new PageVo<VisaItem>(pageNum);
		page.setRows(60);

		List<VisaItem> items = visaItemDao.getVisaItem(page.getOffset(), page.getRows());
		page.setList(items);
		return page;
	}

	public PageVo<VisaItem> getVisaItemByDisplay(int pageNum) {
		PageVo<VisaItem> page = new PageVo<VisaItem>(pageNum);
		page.setRows(60);

		List<VisaItem> items = visaItemDao.getVisaItemByDisplay(page.getOffset(), page.getRows());
		page.setList(items);
		return page;
	}

	public void updateVisaItem(VisaItem visaItem) {
		visaItemDao.updateVisaItem(visaItem);
	}

	public void deleteVisaItem(long id) {
		visaItemDao.deleteVisaItem(id);
	}

	public VisaItem getVisaItemById(long id) {
		return visaItemDao.getVisaItemById(id);
	}

}
