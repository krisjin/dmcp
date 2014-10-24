package com.hx.dmcp.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hx.dmcp.dao.FeedbackDao;
import com.hx.dmcp.entity.vo.FeedbackVo;
import com.hx.dmcp.entity.vo.PageVo;

/**
 * TODO 此处填写 class 信息
 * 
 * @author krisjin
 */
@Service
public class FeedbackService {

	@Autowired
	private FeedbackDao feedbackDao;

	public void addFeedback(FeedbackVo feedback) {
		feedbackDao.addFeedback(feedback);
	}

	public PageVo<FeedbackVo> queryFeedbackWithPage(int pageNum) {
		PageVo<FeedbackVo> page = new PageVo<FeedbackVo>(pageNum);
		page.setRows(10);

		List<FeedbackVo> feedbackList = new ArrayList<FeedbackVo>();
		feedbackList = feedbackDao.getFeedback(page.getOffset(), page.getRows());
		page.setList(feedbackList);
		String a = page.getPageNumHtml();
		return page;
	}

}
