package com.hx.dmcp.mongodb.dao;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hx.dmcp.mongodb.entity.News;


@Repository
public class NewsDao extends BaseDao<News> {
	
	public News getNewsById(String id){
		return this.getMongoTemplate().findOne(new Query(Criteria.where("id").is(id)) , News.class);
	}
	
}
