package com.hx.dmcp.dao;

import java.util.List;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hx.dmcp.entity.Feedback;
import com.hx.dmcp.entity.vo.FeedbackVo;

/**
 * TODO 此处填写 class 信息
 * 
 * @author krisjin
 */
@Repository
public interface FeedbackDao {

	public void addFeedback(Feedback feedback);

	public void updateFeedback(Feedback feedback);

	public void deleteFeedback(@Param("id") long id);

	public List<FeedbackVo> getFeedback(@Param("offset") long offset, @Param("rows") long rows);

	public int getAllCounts();

}
