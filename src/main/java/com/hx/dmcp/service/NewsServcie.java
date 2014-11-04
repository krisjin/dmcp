package com.hx.dmcp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hx.dmcp.mongodb.repository.NewsRepository;
import com.hx.dmcp.store.mongodb.News;

/**
 * @author krisjin
 * @date 2014-11-4
 */
@Service
public class NewsServcie {
	@Autowired
	private NewsRepository newsRepository;

	public long countNews() {

		return newsRepository.count();
	}

	public void deleteNewsById(String id) {
		newsRepository.delete(id);
	}

	public void addNews(News news) {
		newsRepository.save(news);
	}

	public News getNewsById(String id) {
		return newsRepository.findOne(id);

	}

	public NewsRepository getNewsRepository() {
		return newsRepository;
	}

	public void setNewsRepository(NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
	}

}
