package com.icss.oa.message.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.icss.oa.common.Pager;
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
	
	//根据id查询信息
	public Message queryMesById(Integer mesId) {
		return mapper.queryById(mesId);
	}
	
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
	
	/**
	 * 返回信息总记录数
	 */
	@Transactional(readOnly=true)
	public int getMesCount() {
		return mapper.getCount();
	}
	
	//全部的模糊查询
	@Transactional(readOnly=true)
	public List<Message> queryMesByCondition(Pager pager, String mesSendDate, String empEmail, String mesTitle) {
		return mapper.queryByCondition(pager.getStart(), pager.getPageSize(), mesSendDate, empEmail, mesTitle);
	}
	
}
