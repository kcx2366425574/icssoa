package com.icss.oa.system.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.system.dao.NoticeMapper;
import com.icss.oa.system.pojo.Notice;

/**
 * ����ҵ��
 * @author Administrator
 *
 */

@Service
@Transactional(rollbackFor=Exception.class)
public class NoticeService {
	
	@Autowired
	private NoticeMapper mapper;
	
	/**
	 * ���ӹ���
	 */
	public void addNotice(Notice notice){
		mapper.insert(notice);
	}
	
	/**
	 * ɾ������
	 */
	public void deleteNotice(Notice notice){
		mapper.delete(notice.getNoticeId());
	}
	
	/**
	 * ���¹���
	 */
	public void updateNotice(Notice notice){
		mapper.update(notice);
	}
	
	/**
	 * id��ѯ����
	 */
	public Notice queryById(int id){
		return mapper.queryById(id);
	}
	
	/**
	 * name��ѯ����
	 */
	public Notice queryByName(String name){
		return mapper.queryByName(name);
	}
	/**
	 * ��ҳ��ѯ����
	 */
	public List<Notice> query(int start,int pageSize){
		return query(start,pageSize);
	}
}
