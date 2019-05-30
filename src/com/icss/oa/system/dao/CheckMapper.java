package com.icss.oa.system.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.icss.oa.system.pojo.Check;


public interface CheckMapper {

	void insert(Check check);
	int getCount();//query查询结果总数
	int getECount();//querybyid查询结果总数
	List<Check> query(@Param("start") Integer start,@Param("pageSize") Integer pageSize);
	List<Check> queryByEmpId(@Param("checkEmpId")int id,@Param("start") Integer start,@Param("pageSize") Integer pageSize);
	
}
