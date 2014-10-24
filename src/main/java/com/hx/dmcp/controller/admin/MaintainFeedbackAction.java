package com.hx.dmcp.controller.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hx.dmcp.entity.vo.FeedbackVo;
import com.hx.dmcp.entity.vo.PageVo;
import com.hx.dmcp.service.FeedbackService;

/**
 * @author krisjin
 */
@Controller
@RequestMapping("/admin/feedback")
public class MaintainFeedbackAction {

	@Autowired
	private FeedbackService feedbackService;

	@RequestMapping(value = "/list.htm", method = RequestMethod.GET)
	public String queryFeedbackWithPage(@RequestParam(value = "p", defaultValue = "1") int pageNum, ModelMap modelMap) {

		PageVo<FeedbackVo> pageVo = feedbackService.queryFeedbackWithPage(pageNum);
		modelMap.put("pageVo", pageVo);
		return "system/feedback/list";
	}

}
