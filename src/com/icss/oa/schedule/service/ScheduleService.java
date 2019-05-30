package com.icss.oa.schedule.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.schedule.dao.ScheduleMapper;
import com.icss.oa.schedule.pojo.Schedule;

@Service
@Transactional(rollbackFor=Exception.class)
public class ScheduleService {
	@Autowired
	private ScheduleMapper mapper;
	public void addSchedule(Schedule sch)
	{
		mapper.insert(sch);
	}
	public void deleteSchedule(Integer schId)
	{
		mapper.delete(schId);
	}
	public void updateSchedule(Schedule sch)
	{
		mapper.update(sch);
	}
	public Schedule queryBySchId(Integer schId)
	{
		return mapper.queryById(schId);
	}
	public List<Schedule> queryByPage(Pager pager)
	{
		return mapper.queryByPage(pager.getStart(), pager.getPageSize());
	}
	public int getCount()
	{
		return mapper.getCount();
	}
	public List<Schedule> queryByCondition (Pager pager,@Param("empId")Integer e1,@Param("empId")Integer e2,@Param("schName")String schName)
	{
		return mapper.queryByCondition(pager.getStart(), pager.getPageSize(), e1, e2, schName);
	}
	public List<Schedule> queryByIds(@Param("ids") Integer[] ids)
	{
		return mapper.queryByIds(ids);
	}
	public int getCountByCondition(@Param("empId")Integer e1,@Param("empId")Integer e2,@Param("schName")String schName)
	{
		return mapper.getCountByCondition(e1, e2, schName);
	}
}
