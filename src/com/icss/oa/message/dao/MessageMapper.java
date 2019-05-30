package com.icss.oa.message.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.icss.oa.message.pojo.Message;

/**
 * 在线信息dao层
 * 
 * @author bhl
 *
 */
public interface MessageMapper {

	void insert(Message mes); //增加信息

	void update(Message mes); //修改信息

	void delete(Integer mesId); //删除信息

	List<Message> queryByPage(HashMap<String, Integer> map); //查询全部信息，进行分页
	
	List<Message> queryByPage1(@Param("start") Integer start, @Param("pageSize") Integer pageSize);
	
	int getCount(); //获得总数
	
	List<Message> queryByCondition(@Param("start") Integer start, @Param("pageSize") Integer pageSize, //分页
									@Param("mesSendDate") String mesSendDate, //日期
									@Param("empEmail") String empEmail, //邮箱
									@Param("mesTitle") String mesTitle  //题目		
									);
	
}
