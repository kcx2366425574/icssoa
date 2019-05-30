package com.icss.oa.system.service;

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
	 * 插入公告
	 */
	public void addNotice(Notice notice){
		mapper.insert(notice);
	}
	
	/**
	 * 删除公告
	 */
	public void deleteNotice(Integer noticeId){
		mapper.delete(noticeId);
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
	 * 名称查询公告
	 */
	public Notice queryByName(String name){
		return mapper.queryByName(name);
	}
	/**
	 * 分页查询公告
	 */
	public List<Notice> queryNotice(int start,int pageSize){
		return mapper.query(start,pageSize);
	}

	public int getNoticeCount() {
		return mapper.getCount();
	}
	
	
}
