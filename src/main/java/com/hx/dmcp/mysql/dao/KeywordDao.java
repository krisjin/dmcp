package com.hx.dmcp.mysql.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hx.dmcp.mysql.entity.Keyword;

@Repository
public interface KeywordDao {

	public void addKeyword(Keyword keyword);

	public void updateKeyword(Keyword keyword);

	public void deleteKeyword(@Param("id") int id);

	public Keyword getKeywordById(@Param("id") int id);

	public List<Keyword> getKeywordWithPage(@Param("offset") long offset,@Param("rows") long rows);

	public long getTotalKeywordCounts();

}
