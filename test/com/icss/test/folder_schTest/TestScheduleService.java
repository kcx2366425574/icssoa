package com.icss.test.folder_schTest;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.common.Pager;
import com.icss.oa.folder.pojo.Files;
import com.icss.oa.schedule.index.SchIndexDao;
import com.icss.oa.schedule.pojo.Schedule;
import com.icss.oa.schedule.service.ScheduleService;
import com.icss.oa.system.pojo.Employee;

public class TestScheduleService {
	
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			ScheduleService service=context.getBean(ScheduleService.class);
			SchIndexDao indexDao=context.getBean(SchIndexDao.class);
		@Test
		public void testAddSchedule()
		{
			Employee emp1=new Employee();
			emp1.setEmpId(5);
			Employee emp2=new Employee();
			emp2.setEmpId(4);
			Schedule schedule=new Schedule("zed", "哈，我什么都不知道", Date.valueOf("2013-12-21"), Date.valueOf("2014-01-21"), Date.valueOf("2014-03-11"),emp1 , emp2);
			service.addSchedule(schedule);
		}
		/**
		 * 重建员工的索引
		 */
		@Test
		public void testCreateIndex(){
			
			
			List<Schedule> list = service.querySchedule();
			
			for (Schedule sch : list) {
							
				System.out.println(sch);
				
				try {
					/********** 生成索引 *************/
					// 创建索引文档
					Document document = new Document();
					document.add(new TextField("schId", String.valueOf(sch.getSchId())  , Store.YES));
					document.add(new TextField("schName", sch.getSchName(), Store.YES));
					document.add(new TextField("schInfo", sch.getSchInfo(), Store.YES));

					// 调用索引dao
					indexDao.create(document);
					
					System.out.println("索引已创建");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}	
			
		}
		@Test
		public void testDeleteSchedule()
		{
			service.deleteSchedule(7);
		}
		@Test
		public void testUpdateSchedule()
		{
			Employee emp3=new Employee();
			emp3.setEmpId(4);
			Employee emp4=new Employee();
			emp4.setEmpId(4);
		    Schedule schedule=new Schedule(4, "屌丝", "干啥啥不行，吃啥啥没够", Date.valueOf("2013-12-29"), Date.valueOf("2014-01-25"), Date.valueOf("2014-02-11"),emp3 , emp4);
		    service.updateSchedule(schedule);
		}
		@Test
		public void testQueryById()
		{
			System.out.print(service.queryBySchId(3));
		}
		@Test
		public void testQueryByPage()
		{
			Pager pager=new Pager(service.getCount(), 6, 2);
			List<Schedule> list=service.queryByPage(pager);
			for(Schedule sch:list)
			{
				System.out.println(sch);
			}
			
		}
		@Test
		public void testGetCount()
		{
			System.out.println(service.getCount());
		}
		@Test
		public void testQueryByCondition()
		{
			Pager pager=new Pager(service.getCount(), 5, 2);
			List<Schedule> list=service.queryByCondition(pager, 1, 5, "s");
			for(Schedule sch:list)
			{
				System.out.println(sch);
			}
		}
		@Test
		public void testGetCountByCondition()
		{
			System.out.println(service.getCountByCondition(2, 3, "s"));
		}
		@Test
	  	public void testqueryByIds()
	  	{
	  		Integer[] ids = {2,3,4};
	  		List<Schedule> list=service.queryByIds(ids);
	  		for (Schedule sch : list) {
				System.out.println(sch);
			}
	  	}
}
