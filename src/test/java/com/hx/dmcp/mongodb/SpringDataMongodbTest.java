package com.hx.dmcp.mongodb;

import java.net.UnknownHostException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hx.dmcp.mongodb.entity.News;
import com.mongodb.MongoClient;

/**
 * @author krisjin
 * @date 2014-11-5
 */
public class SpringDataMongodbTest {
	private static String dbName = "blog";
	private static String newsCollection = "news";
	private static String mongoHost = "127.0.0.1";
	private static int mongoPort = 27017;

	private static MongoClient mongoClient;
	private static MongoTemplate mongoOps;

//	@Test
	public void addNews(){
		News news = new News();
		news.setId(2);
		news.setNewsTitle("索尼移动中国区陷裁员风波：业务不佳 员工焦虑");
		news.setNewsAuthor("aa");
		news.setNewsMedia("tecent");
		news.setNewsUrl("");
		
		mongoOps.insert(news,newsCollection);
		
		
	}
	
	@Test
	public void getNews(){
		System.out.println("--------------------");
		News news =mongoOps.findById("5459b2a1be2cf6f0f2a86bb7", News.class);
		List<News> newsList = mongoOps.find(new Query(Criteria.where("newsType").is(0)), News.class,newsCollection);
		for(News n:newsList){
			
			System.out.println(n);
		}
		
	}
	
	@Test
	public void updateNews(){
		News news = mongoOps.findOne(new Query(Criteria.where("newsType").is(0)), News.class);
		news.setNewsAuthor("vvvv");
		mongoOps.save(news);
	}
	
	@Test
	public void deleeteNews(){
		mongoOps.remove(new Query(Criteria.where("id").is(1)), News.class);
	}
	
	@BeforeClass
	public static void init() {
		try {
			mongoClient = new MongoClient(mongoHost, mongoPort);
			mongoOps = new MongoTemplate(mongoClient, dbName);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}
}
