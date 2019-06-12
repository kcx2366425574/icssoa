package com.icss.oa.reb.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.reb.dao.RebMapper;
import com.icss.oa.reb.pojo.Reb;


/**
 * 部门业务
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class RebService {
	
	@Autowired                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
	private RebMapper mapper;
	
	/**
	 * 增加部门
	 */
	
	public void addReb(Reb reb) {		
		mapper.insert(reb);		
	}	
	
	/**
	 * 更新部门
	 */
	public void updateReb(Reb reb) {
		mapper.update(reb);
	}

	/**
	 * 删除部门
	 */
	public void deleteReb(Integer rebId) {
		mapper.delete(rebId);
	}
	
	/**
	 *  id查询部门
	 */
	@Transactional(readOnly=true)
	public Reb queryRebById(Integer rebId) {
		return mapper.queryById(rebId);
	}
	
	/**
	 * 查询所有部门
	 */
	@Transactional(readOnly=true)
	public List<Reb> queryReb(int start,int pageSize) {
		return mapper.query(start,pageSize);
	}

	public int getRebCount() {
		
		return mapper.getCount();
	}
	
}