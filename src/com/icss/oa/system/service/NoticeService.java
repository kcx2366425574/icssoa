package com.icss.oa.system.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.system.dao.NoticeMapper;
import com.icss.oa.system.pojo.Notice;

/**
 * 公告业务
 * @author Administrator
 *
 */

@Service
@Transactional(rollbackFor=Exception.class)
public class NoticeService {
	
	@Autowired
	private NoticeMapper mapper;
	
	/**
	 * 增加公告
	 */
	public void addNotice(Notice notice){
		mapper.insert(notice);
	}
	
	/**
	 * 删除公告
	 */
	public void deleteNotice(Notice notice){
		mapper.delete(notice.getNoticeId());
	}
	
	/**
	 * 更新公告
	 */
	public void updateNotice(Notice notice){
		mapper.update(notice);
	}
	
	/**
	 * id查询公告
	 */
	public Notice queryById(int id){
		return mapper.queryById(id);
	}
	
	/**
	 * name查询公告
	 */
	public Notice queryByName(String name){
		return mapper.queryByName(name);
	}
	/**
	 * 分页查询公告
	 */
	public List<Notice> query(int start,int pageSize){
		return query(start,pageSize);
	}
}
