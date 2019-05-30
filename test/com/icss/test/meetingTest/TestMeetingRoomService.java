package com.icss.test.meetingTest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.meeting.pojo.MeetingRoom;
import com.icss.oa.meeting.service.MeetingRoomService;

/**
 * 
 * @author Administrator
 *
 */

public class TestMeetingRoomService {

	// 获得spring核心容器对象
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		// 获得mapper对象(dao对象）
		MeetingRoomService mapper = context.getBean(MeetingRoomService.class);
		
		@Test
		public void test(){
			MeetingRoom meetingRoom=mapper.queryMeetingRoomById(1);
			System.out.println(meetingRoom);
		}
}
