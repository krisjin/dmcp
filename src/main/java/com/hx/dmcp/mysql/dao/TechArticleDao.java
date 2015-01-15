package com.hx.dmcp.mysql.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hx.dmcp.mysql.entity.TechArticle;

/**
 * 技术文章数据访问接口
 * @author krisjin
 * @date 2015-1-15
 */
@Repository
public interface TechArticleDao {

	public void addTechArticle(TechArticle techArticle);

	public void updateTechArticle(TechArticle techArticle);

	public void deleteTechArticle(@Param("articleId") long articleId);

	public TechArticle getTechArticleById(@Param("articleId") long articleId);

	public List<TechArticle> getTechArticleWithPage(@Param("offset") long offset, @Param("rows") long rows);

	public long getCounts();
	
	public List<TechArticle> getChartTechArticle(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
