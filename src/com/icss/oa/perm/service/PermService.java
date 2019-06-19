package com.icss.oa.perm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.perm.dao.PermissionMapper;
import com.icss.oa.perm.dao.RoleMapper;

/**
 * 权限service
 * @author Administrator
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class PermService {
	
	@Autowired
	private PermissionMapper permMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	/**
	 * 查询指定用户所有权限名称
	 * @param empLoginName
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Map<String, Object>> queryPerm(String empLoginName) {
		
		return permMapper.queryPerm(empLoginName);		
	}	
	
	/**
	 * 查询指定用户所有的角色
	 * @param empLoginName
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Map<String, Object>> queryRole(String empLoginName) {
		
		return roleMapper.queryRole(empLoginName);		
	}

}