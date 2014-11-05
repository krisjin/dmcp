package com.hx.dmcp.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hx.dmcp.mongodb.entity.News;
import com.hx.dmcp.service.NewsService;

/**
 * @author krisjin
 * @date 2014-11-5
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/admin/news")
public class NewsController {

	@Autowired
	private NewsService newsSerivce;

	@RequestMapping(value = "/detail.htm", method = RequestMethod.GET)
	public String getNews(@RequestParam(value = "id") String id,ModelMap model) {
		News news =newsSerivce.getNewsById(id);
		model.put("news", news);
		return "page/news/newsDetail";
	}

	@RequestMapping(value = "/news.htm", method = RequestMethod.GET)
	public String listNews(ModelMap model) {
		List<News> news =newsSerivce.findNewsWithPage();
		model.put("newsList", news);
		return "page/news/listNews";
	}

	
	
	
	
	
	public NewsService getNewsSerivce() {
		return newsSerivce;
	}

	public void setNewsSerivce(NewsService newsSerivce) {
		this.newsSerivce = newsSerivce;
	}
	
}
