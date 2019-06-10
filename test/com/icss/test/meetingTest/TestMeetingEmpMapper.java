package com.icss.test.meetingTest;

import java.util.List;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.icss.oa.meeting.dao.MeetingEmpMapper;
import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingEmp;
import com.icss.oa.system.pojo.Employee;

public class TestMeetingEmpMapper {
	// 获得spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 获得mapper对象(dao对象）
	MeetingEmpMapper mapper = context.getBean(MeetingEmpMapper.class);

	@Test
	// 测试插入操作
	public void testInsert() {

		// 创建pojo对象
		Employee emp = new Employee();
		emp.setEmpId(4);

		Meeting meeting = new Meeting();
		meeting.setMeetingId(4);
		MeetingEmp meetingEmp = new MeetingEmp(meeting, emp);
		// 调用数据
		mapper.insert(meetingEmp);

	}

	// 测试删除操作
	@Test
	public void testdelete() {
		// 调用数据
		mapper.delete(12);

	}

	// 测试动态查询
	@Test
	public void testQueryByCondition() {

		List<MeetingEmp> list = mapper.queryByCondition(0, 5, null, null, null);

		for (MeetingEmp meetingEmp : list) {
			System.out.println(meetingEmp);
		}
	}

	// 测试条件查询会议室数量操作
	@Test
	public void testGetmeetingEmpCountCondition() {
		// 调用数据
		Integer m = mapper.getMeetingEmpCountCondition(null, null, null);
		System.out.println("记录数是：" + m);

	}
}
