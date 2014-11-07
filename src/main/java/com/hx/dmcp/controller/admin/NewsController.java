package com.hx.dmcp.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.base.Strings;
import com.hx.dmcp.mongodb.entity.News;
import com.hx.dmcp.service.NewsService;
import com.hx.dmcp.util.DateUtil;
import com.hx.dmcp.util.Pagination;

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
	public String getNews(@RequestParam(value = "id") String id, ModelMap model) {
		News news = newsSerivce.getNewsById(id);
		model.put("news", news);
		return "page/news/newsDetail";
	}

	@RequestMapping(value = "/list.htm", method = RequestMethod.GET)
	public String listNews(@RequestParam(value = "p", defaultValue = "1") int p,
			@RequestParam(value = "newsPosttime", defaultValue = "") String newsPosttime, ModelMap model) {

		Pagination<News> page = new Pagination<News>();
		page.setCurrentPageSize(p);
		page.setPerPageRecords(15);
		List<News> news =null;
		page.setTotalRecords(newsSerivce.getCountsNews());

		if (Strings.isNullOrEmpty(newsPosttime)) {
			news = newsSerivce.findNewsWithPage(page, null);
		} else {
				news = newsSerivce.findNewsWithPage(page, newsPosttime);
		}

		page.setData(news);
		Map args = new HashMap();

		Date n = news.get(news.size() - 1).getNewsPosttime();
		if (n != null) {
			String a = DateUtil.format(n, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
			args.put("newsPosttime", a);
		}
		page.setArgs(args);
		model.put("page", page);
		return "page/news/listNews";
	}

	public NewsService getNewsSerivce() {
		return newsSerivce;
	}

	public void setNewsSerivce(NewsService newsSerivce) {
		this.newsSerivce = newsSerivce;
	}

}
