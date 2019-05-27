package com.icss.oa.message.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.message.pojo.Message;

/**
 * ������Ϣdao��
 * 
 * @author bhl
 *
 */
public interface MessageMapper {

	void insert(Message mes);

	void update(Message mes);

	void delete(Integer mesId);

	Message queryById(Integer mesId);

	List<Message> queryByPage(HashMap<String, Integer> map); //��ѯȫ����Ϣ�����з�ҳ

	List<Message> queryBySendDate(Date mesSendDate); //�������ڲ�ѯ��Ϣ

	List<Message> queryByReciverEmail(String mesEmail); //�����ռ��˵������ѯ��Ϣ

	List<Message> queryByDateCondition(@Param("start") Integer start, @Param("pageSize") Integer pageSize,
			@Param("mesSendDate") String mesSendDate); //�������ڽ���ģ����ѯ

	List<Message> queryByReciverCondition(@Param("start") Integer start, @Param("pageSize") Integer pageSize,
			@Param("empEmail") String empEmail); //�����ռ����������ģ����ѯ
	
	List<Message> queryByTitleCondition(@Param("start") Integer start, @Param("pageSize") Integer pageSize,
			@Param("mesTitle") String mesTitle); //���ݷ�����Ŀ����ģ����ѯ
	
}
