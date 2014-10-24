package com.hx.dmcp.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hx.dmcp.entity.vo.FeedbackVo;
import com.hx.dmcp.service.FeedbackService;
import com.hx.dmcp.util.HttpUtils;

/**
 * TODO 此处填写 class 信息
 * 
 * @author krisjin
 */

@Controller
@RequestMapping("/feedbacks")
public class FeedbackAction {

	@Autowired
	private FeedbackService feedbackService;

	@RequestMapping(value = "/add.htm", method = RequestMethod.POST)
	public String addFeedback(@RequestParam(value = "username") String username, @RequestParam(value = "email") String email,
			@RequestParam(value = "phone") String phone, @RequestParam(value = "content") String content, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {
		FeedbackVo fav = new FeedbackVo();

		fav.setContent(content);
		fav.setName(username);
		fav.setEmail(email);
		fav.setPhone(phone);
		fav.setCreateDate(new Date());
		feedbackService.addFeedback(fav);

		String path = HttpUtils.getBasePath(request) + "/board/index.htm";
		try {
			response.sendRedirect(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
