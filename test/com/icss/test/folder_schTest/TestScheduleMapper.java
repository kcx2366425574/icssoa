package com.icss.test.folder_schTest;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.folder.pojo.Folder;
import com.icss.oa.schedule.dao.ScheduleMapper;
import com.icss.oa.schedule.pojo.Schedule;
import com.icss.oa.system.pojo.Employee;

public class TestScheduleMapper {
	
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			ScheduleMapper mapper=context.getBean(ScheduleMapper.class);
			@Test
			public void testInsert()
			{
				Employee emp=new Employee();
				emp.setEmpId(3);
				Employee employee=new Employee();
				employee.setEmpId(2);
				Schedule sch=new Schedule("北京开会", "参加全国人代会", Date.valueOf("2010-10-21"), Date.valueOf("2011-01-21"), Date.valueOf("2011-03-01"),emp , employee);
				mapper.insert(sch);
			}
			@Test
			public void testDelete()
			{
				mapper.delete(1);
			}
			@Test
			public void testUpdate()
			{
				Employee emp1=new Employee();
				emp1.setEmpId(5);
				Employee emp2=new Employee();
				emp2.setEmpId(3);
				Schedule schedule=new Schedule(4, "我不知道", "你自己去猜", Date.valueOf("2010-01-10"), Date.valueOf("2010-07-14"), Date.valueOf("2011-01-12"), emp1, emp2);
				mapper.update(schedule);
			}
			@Test
			public void testQueryById()
			{
				System.out.println(mapper.queryById(6));
			}
			@Test
			public void testQueryByPage()
			{
				List<Schedule> list=mapper.queryByPage(4, 6);
				for(Schedule sch:list)
		    	{
		    		System.out.println(sch);
		    	}
			}
			@Test
			public void testGetCount()
			{
				System.out.println(mapper.getCount());
			}
			@Test
			public void testQueryByCondition()
			{
				List<Schedule> list=mapper.queryByCondition(5, 5, 2, 3, "z");
				for(Schedule sch:list)
				{
					System.out.println(sch);
				}
			}
			@Test 
			public void testQueryByIds()
			{
				Integer[] ids={4,5};
				List<Schedule> list=mapper.queryByIds(ids);
				for(Schedule sch:list)
				{
					System.out.println(sch);
				}
			}
			@Test
			   public void testGetCountByCondition()
			    {
			    	
			    	System.out.println(mapper.getCountByCondition(2,2,"b"));
			    }
}
