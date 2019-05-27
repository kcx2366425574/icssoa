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
 * ������Ϣҵ���
 * @author bhl
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MessageService {

	@Autowired
	private MessageMapper mapper;
	
	//�����Ϣ
	public void addMes(Message mes){
		mapper.insert(mes);
	}
	
	//ɾ����Ϣ
	public void deleteMes(Integer mesId) {
		mapper.delete(mesId);
	}
	
	//�޸���Ϣ
	public void updateMes(Message mes) {
		mapper.update(mes);
	}
	
	//����id��ѯ��Ϣ
	public Message queryMesById(Integer mesId) {
		return mapper.queryById(mesId);
	}
	
	//��ѯȫ����Ϣ
	public List<Message> queryMesByPage(HashMap<String, Integer> map) {
		return mapper.queryByPage(map);
	}
	
	//���ݷ���ʱ���ѯ��Ϣ
	public List<Message> queryMesSendDate(Date mesSendDate) {
		return mapper.queryBySendDate(mesSendDate);
	}
	
	//�����ռ��������ѯ��Ϣ
	public List<Message> queryByReciverEmail(String mesEmail) {
		return mapper.queryByReciverEmail(mesEmail);
	}
	
	//�������ڽ���ģ����ѯ
	public List<Message> queryByDateCondition(Integer start, Integer pageSize, String mesSendDate) {
		return mapper.queryByDateCondition(start, pageSize, mesSendDate);
	}
	
	//�����ռ����������ģ����ѯ
	public List<Message> queryByReciverCondition(Integer start, Integer pageSize, String mesEmail) {
		return mapper.queryByReciverCondition(start, pageSize, mesEmail);
	}
	
	//������Ŀģ����ѯ
	public List<Message> queryByTitleCondition(Integer start, Integer pageSize, String mesTitle) {
		return mapper.queryByTitleCondition(start, pageSize, mesTitle);
	}
}
