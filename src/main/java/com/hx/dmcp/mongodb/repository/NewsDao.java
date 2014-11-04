package com.hx.dmcp.mongodb.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.hx.dmcp.mongodb.entity.News;
import com.hx.dmcp.store.springmongodb.common.MongodbBaseDao;

public class NewsDao extends MongodbBaseDao<News> {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	

	@Override
	protected Class<News> getEntityClass() {
		return News.class;
	}

	@Override
	protected void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public void addNews(News news){
		mongoTemplate.insert(news);
		
	}
	

}
