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
import com.icss.oa.meeting.index.MeetingIndexDao;

import com.icss.oa.meeting.pojo.Meeting;


import com.icss.oa.meeting.service.MeetingService;

public class TestMeetingService {
	
	// 获得spring核心容器对象
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

			// 获得mapper对象(dao对象）
			MeetingService service = context.getBean(MeetingService.class);
			
			MeetingIndexDao indexDao = context.getBean(MeetingIndexDao.class);
			
			/**
			 * 重建会议的索引
			 */
			@Test
			public void testCreateIndex(){
				
				Pager pager = new Pager(service.getMeetingCount(),service.getMeetingCount() ,1);
				List<Meeting> list = service.queryMeeting();
				
				for (Meeting meeting : list) {
								
					System.out.println(meeting);
					
					try {
						/********** 生成索引 *************/
						// 创建索引文档
						Document document = new Document();
						document.add(new TextField("meetingId", String.valueOf(meeting.getMeetingId())  , Store.YES));
						document.add(new TextField("meetingTheme", meeting.getMeetingTheme(), Store.YES));
						document.add(new TextField("meetingState", meeting.getMeetingState(), Store.YES));
						

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
				
				List<Meeting> list = service.queryMeetingByIndex("例会");
				
				for (Meeting meeting : list) {
					System.out.println(meeting);
				}	
				
			}

}
