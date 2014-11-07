package com.hx.dmcp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hx.dmcp.entity.Function;

public interface FunctionDao {
	
	public void addFunction(Function function);

	public void updateFunction(Function function);
	
	public void deleteFunction(@Param("functionId") int functionId);
	
	public Function findFunctionById(@Param("functionId") int functionId);
	
	public List<Function> findFunctionByIds(@Param("ids") String ids);
	
}
