package com.icss.oa.system.dao;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.icss.oa.system.pojo.Sign;


public interface SignMapper {

	//增加签到
	void insert(@Param("signEmpId") Integer signEmpId,@Param("signTime") Date signTime);
	
	//query查询结果总数
	int getAllCount(@Param("signEmpId")Integer signEmpId);
	
	//查询所有的签到记录
	List<Sign> query(@Param("signEmpId")Integer signEmpId, @Param("start") Integer start,@Param("pageSize") Integer pageSize);
	
	
}
