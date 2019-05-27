package com.icss.oa.message.dao;

import java.util.Date;
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

	void insert(Message mes);

	void update(Message mes);

	void delete(Integer mesId);

	Message queryById(Integer mesId);

	List<Message> queryByPage(HashMap<String, Integer> map); //查询全部信息，进行分页

	List<Message> queryBySendDate(Date mesSendDate); //根据日期查询信息

	List<Message> queryByReciverEmail(String mesEmail); //根据收件人的邮箱查询信息

	List<Message> queryByDateCondition(@Param("start") Integer start, @Param("pageSize") Integer pageSize,
			@Param("mesSendDate") String mesSendDate); //根据日期进行模糊查询

	List<Message> queryByReciverCondition(@Param("start") Integer start, @Param("pageSize") Integer pageSize,
			@Param("empEmail") String empEmail); //根据收件人邮箱进行模糊查询
	
	List<Message> queryByTitleCondition(@Param("start") Integer start, @Param("pageSize") Integer pageSize,
			@Param("mesTitle") String mesTitle); //根据发送题目进行模糊查询
	
}
