package com.hx.dmcp.mongodb.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hx.dmcp.mongodb.entity.News;
import com.hx.dmcp.util.Pagination;

/**
 * @author krisjin
 * @date 2014-11-6
 */
@Repository
public class NewsDao extends BaseDao<News> {

	public News getNewsById(String id) {
		return this.getMongoTemplate().findOne(new Query(Criteria.where("id").is(id)), News.class);
	}

	public List<News> getNewsWithPageByPosttime(Pagination<News> page, String newsPosttime) {

		Direction direction = Direction.DESC;
		Query query = new Query();

		query.with(new Sort(direction, "newsPosttime"));
		if (page.getCurrentPageSize() > 1) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = null;
			try {
				date = sdf.parse(newsPosttime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			query.addCriteria(Criteria.where("newsPosttime").lt(date)).limit(page.getPerPageRecords());
		} else {
			query.limit(page.getPerPageRecords());
		}
		return this.getMongoTemplate().find(query, News.class);
	}

	public List<News> getNewsWithPage(Pagination<News> page) {
		Direction direction = Direction.DESC;
		Query query = new Query();
		query.with(new Sort(direction, "newsPosttime"));
		query.skip(page.getOffsetRecords());
		query.limit(page.getPerPageRecords());
	
		
		
		return this.getMongoTemplate().find(query, News.class);
	}

}
