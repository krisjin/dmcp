package com.hx.dmcp.mongodb.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hx.dmcp.entity.User;
import com.hx.dmcp.store.mongodb.News;

@Repository
public class NewsRepository extends BaseRepository {

	public long count() {

		return this.getMongoOperations().count(new Query(), News.class);
	}

	public void delete(Serializable id) {
		this.getMongoOperations().remove(getIdQuery(id), News.class);
	}

	public void deleteAll() {
		this.getMongoOperations().remove(new Query(), News.class);
	}

	public boolean exists(Serializable id) {

		return this.getMongoOperations().exists(getIdQuery(id), News.class);
	}

	public List findAll() {

		return this.getMongoOperations().find(new Query(), News.class);
	}

	public Iterable findAll(Iterable ids) {

		return null;
	}

	public Page findAll(Pageable pageable) {

		return null;
	}

	public List findAll(Sort sort) {

		return null;
	}

	public News findOne(Serializable id) {

		return this.getMongoOperations().findOne(getIdQuery(id), News.class);
	}
}
