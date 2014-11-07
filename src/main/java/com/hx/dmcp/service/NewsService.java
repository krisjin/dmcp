package com.hx.dmcp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hx.dmcp.mongodb.dao.NewsDao;
import com.hx.dmcp.mongodb.entity.News;
import com.hx.dmcp.util.Pagination;

/**
 * @author krisjin
 * @date 2014-11-5
 */
@Service
public class NewsService {

	@Autowired
	private NewsDao newsDao;

	public void addNews(News entity) {
		newsDao.save(entity);
	}

	public void updateNews(News news) {
		newsDao.update(news);
	}

	public News getNewsById(String id) {
		return newsDao.getNewsById(id);
	}

	public void deleteNews(News news) {
		newsDao.delete(news);
	}

	public void deleteNewsById(String id) {
		newsDao.deleteById(id);
	}
	
	
	public List<News> findNewsWithPage(Pagination<News> page ,String newsPosttime){
		return newsDao.getNewsWithPage(page);
		//return newsDao.getNewsWithPageByPosttime(page, newsPosttime);
	}
	
	public long getCountsNews(){
		
		return newsDao.count();
	}
	
}
