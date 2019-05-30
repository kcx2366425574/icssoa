package com.icss.oa.message.service;

import java.util.HashMap;
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
	
	//查询全部信息
	@Transactional(readOnly = true)
	public List<Message> queryMesByPage(HashMap<String, Integer> map) {
		return mapper.queryByPage(map);
	}
	
	@Transactional(readOnly = true)
	public List<Message> queryByPage(Integer start, Integer pageSize) {
		return mapper.queryByPage1(start, pageSize);
	}
	
	/**
	 * 返回信息总记录数
	 */
	@Transactional(readOnly=true)
	public int getMesCount() {
		return mapper.getCount();
	}
	
	/**
	 * 分页查询信息
	 */
	@Transactional(readOnly=true)
	public List<Message> queryMesByPage(Pager pager) {
		return mapper.queryByPage1(pager.getStart(), pager.getPageSize());
	}
	
	//全部的模糊查询
	@Transactional(readOnly=true)
	public List<Message> queryMesByCondition(Integer start, Integer pageSize, String mesSendDate, String empEmail, String mesTitle) {
		return mapper.queryByCondition(start, pageSize, mesSendDate, empEmail, mesTitle);
	}

}
