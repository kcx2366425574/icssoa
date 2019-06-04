package com.icss.oa.system.dao;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.icss.oa.system.pojo.Sign;


public interface SignMapper {

	//增加签到
	void insert(@Param("signEmpId") Integer empId,@Param("signTime") Date signTime);
	
	//query查询结果总数
	int getCountByEmpId(Integer signEmpId);
	
	//查询所有的签到记录
	List<Sign> query(@Param("start") Integer start,@Param("pageSize") Integer pageSize);
	
	//查询该员工所有的签到记录
	List<Sign> queryByEmpId(@Param("signEmpId")int signEmpId,@Param("start") Integer start,@Param("pageSize") Integer pageSize);
	
}
