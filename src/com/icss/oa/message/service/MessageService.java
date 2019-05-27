package com.icss.oa.message.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.icss.oa.message.dao.MessageMapper;
import com.icss.oa.message.pojo.Message;

/**
 * 在线信息业务层
 * @author bhl
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MessageService {

	@Autowired
	private MessageMapper mapper;
	
	//添加信息
	public void addMes(Message mes){
		mapper.insert(mes);
	}
	
	//删除信息
	public void deleteMes(Integer mesId) {
		mapper.delete(mesId);
	}
	
	//修改信息
	public void updateMes(Message mes) {
		mapper.update(mes);
	}
	
	//根据id查询信息
	public Message queryMesById(Integer mesId) {
		return mapper.queryById(mesId);
	}
	
	//查询全部信息
	public List<Message> queryMesByPage(HashMap<String, Integer> map) {
		return mapper.queryByPage(map);
	}
	
	//根据发送时间查询信息
	public List<Message> queryMesSendDate(Date mesSendDate) {
		return mapper.queryBySendDate(mesSendDate);
	}
	
	//根据收件人邮箱查询信息
	public List<Message> queryByReciverEmail(String mesEmail) {
		return mapper.queryByReciverEmail(mesEmail);
	}
	
	//根据日期进行模糊查询
	public List<Message> queryByDateCondition(Integer start, Integer pageSize, String mesSendDate) {
		return mapper.queryByDateCondition(start, pageSize, mesSendDate);
	}
	
	//根据收件人邮箱进行模糊查询
	public List<Message> queryByReciverCondition(Integer start, Integer pageSize, String mesEmail) {
		return mapper.queryByReciverCondition(start, pageSize, mesEmail);
	}
	
	//根据题目模糊查询
	public List<Message> queryByTitleCondition(Integer start, Integer pageSize, String mesTitle) {
		return mapper.queryByTitleCondition(start, pageSize, mesTitle);
	}
}
