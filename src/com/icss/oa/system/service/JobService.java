package com.icss.oa.system.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.icss.oa.system.dao.JobMapper;
import com.icss.oa.system.pojo.Job;

/**
 * ����ҵ��
 * @author Administrator
 *
 */

@Service
@Transactional(rollbackFor=Exception.class)
public class JobService {

	@Autowired
	public JobMapper mapper;
	
	/**
	 * ����ְ��
	 */
	public void addJob(Job job){
		mapper.insert(job);
	}
	
	/**
	 * �޸�ְ��
	 */
	public void updateJob(Job job) {
		mapper.update(job);
	}
	
	/**
	 * ɾ��ְ��
	 */
	public void deleteJob(Integer jobId) {
		mapper.delete(jobId);
	}
	
	/**
	 * ͨ��id��ѯְ��
	 */
	@Transactional(readOnly=true)
	public Job queryById(Integer jobId){
		return mapper.queryById(jobId);
	}
	
	/**
	 * ��ѯ����ְ��
	 */
	@Transactional(readOnly=true)
	public List<Job> queryJob(int start,int pageSize){
		return mapper.query(start,pageSize);
	}

	public int getJobCount() {
		// TODO Auto-generated method stub
		return mapper.getCount();
	}
}
