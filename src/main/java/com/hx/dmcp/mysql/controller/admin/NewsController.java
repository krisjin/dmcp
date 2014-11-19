package com.hx.dmcp.mysql.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hx.dmcp.mysql.entity.News;
import com.hx.dmcp.mysql.service.NewsService;
import com.hx.dmcp.util.Pagination;

/**
 * @author krisjin
 * @date 2014-11-18
 */
@Controller
@RequestMapping("/admin/ms")
public class NewsController {

	@Autowired
	private NewsService newsService;

	@RequestMapping(value = "/news.htm", method = RequestMethod.GET)
	public String listNews(@RequestParam(value = "p", defaultValue = "1") int p, ModelMap model) {
		Pagination<News> page = newsService.getNewsWithPage(p, 15);
		model.put("page", page);
		return "page/news/listNews";
	}

	@RequestMapping(value = "/news/{id}.htm", method = RequestMethod.GET)
	public String newsDetail(@PathVariable("id") long id, ModelMap model) {
		News news = newsService.getNewsById(id);
		model.put("news", news);
		return "page/news/newsDetail";
	}

	@RequestMapping(value = "/inflation.htm", method = RequestMethod.GET)
	public String inflationStatistics(ModelMap model){
		List<News> newsList =new ArrayList<News>();
		model.put("newsList", newsList);
		return "page/inflation/inflation-mysql";
	}
	
	@RequestMapping(value = "/inflation/chart.htm", method = RequestMethod.POST)
	public String inflationStatisticsChart(@RequestParam(value="startDate") String startDate,@RequestParam(value="endDate") String endDate,ModelMap model){
		List<News> newsMap =newsService.getInfationNews(startDate, endDate);
		
		model.put("newsList", newsMap);
		return "page/inflation/inflation-mysql";
	}
	
	
	
	
}
