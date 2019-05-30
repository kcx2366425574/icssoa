package com.icss.test.meetingTest;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.meeting.dao.MeetingEmpMapper;
import com.icss.oa.meeting.dao.MeetingMapper;
import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingEmp;
import com.icss.oa.meeting.pojo.MeetingRoom;
import com.icss.oa.system.pojo.Employee;

public class TestMeetingEmpMapper {
	// 获得spring核心容器对象
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		// 获得mapper对象(dao对象）
		MeetingEmpMapper mapper = context.getBean(MeetingEmpMapper.class);

//		@Test
//		// 测试插入操作
//		public void testInsert()  {
//			
//			// 创建pojo对象
//			MeetingEmp meetingEmp = new MeetingEmp(1,1);
//			// 调用数据
//			mapper.insert(meetingEmp);
//
//		}
		
		// 测试删除操作
		@Test
		public void testdelete() {
			// 调用数据
			mapper.delete(22);

		}
		
		//测试动态查询
				@Test
				public void testQueryByCondition() {		
							
					List<MeetingEmp> list = mapper.queryByCondition(0,5,null,null,3);
					
					for (MeetingEmp meetingEmp : list) {
						System.out.println(meetingEmp);
					}
				}
}
