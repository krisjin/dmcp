package com.hx.dmcp.mysql.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hx.dmcp.mysql.entity.Keyword;

public interface KeywordDao {

	public void addKeyword(Keyword keyword);

	public void updateKeyword(Keyword keyword);

	public void deleteKeyword(@Param("id") long id);

	public List<Keyword> getKeywordById(@Param("id") String id);

	public List<Keyword> getKeywordWithPage(@Param("offset") long offset, @Param("rows") long rows);

	public long getTotalKeywordCounts();
	
}
