package com.icss.test.meetingTest;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.common.Pager;
import com.icss.oa.meeting.index.MeetingRoomIndexDao;
import com.icss.oa.meeting.pojo.MeetingRoom;
import com.icss.oa.meeting.service.MeetingRoomService;
import com.icss.oa.system.pojo.Employee;


public class TestMeetingRoomService {
	//获得Spring核心容器对象
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//获得Service对象
		MeetingRoomService service = context.getBean(MeetingRoomService.class);
		
		MeetingRoomIndexDao indexDao = context.getBean(MeetingRoomIndexDao.class);
		
		/**
		 * 重建员工的索引
		 */
		@Test
		public void testCreateIndex(){
			
			Pager pager = new Pager(service.getMeetingRoomCount(),service.getMeetingRoomCount() ,1);
			List<MeetingRoom> list = service.queryMeetingRoomByPage(pager);
			
			for (MeetingRoom meetingRoom : list) {
							
				System.out.println(meetingRoom);
				
				try {
					/********** 生成索引 *************/
					// 创建索引文档
					Document document = new Document();
					document.add(new TextField("meetingRoomId", String.valueOf(meetingRoom.getMeetingRoomId())  , Store.YES));
					document.add(new TextField("meetingRoomName", meetingRoom.getMeetingRoomName(), Store.YES));
					document.add(new TextField("meetingRoomLocation", meetingRoom.getMeetingRoomLocation(), Store.YES));
					document.add(new TextField("meetingRoomCondition", meetingRoom.getMeetingRoomCondition(), Store.YES));
					document.add(new TextField("meetingRoomSize", String.valueOf(meetingRoom.getMeetingRoomSize())  , Store.YES));

					// 调用索引dao
					indexDao.create(document);
					
					System.out.println("索引已创建");
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}	
			
		}
		

		/**
		 * 测试全文检索
		 * @throws IOException 
		 * @throws ParseException 
		 * @throws InvalidTokenOffsetsException 
		 */
		@Test
		public void testQueryByIndex() throws ParseException, IOException, InvalidTokenOffsetsException {
			
			List<MeetingRoom> list = service.queryMeetingRoomByIndex("30");
			
			for (MeetingRoom meetingRoom : list) {
				System.out.println(meetingRoom);
			}	
			
		}
		

}
