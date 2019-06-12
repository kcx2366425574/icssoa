package com.icss.test.meetingTest;
/**
 * 测试会议类Mapper
 */

import static org.hamcrest.CoreMatchers.nullValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.meeting.dao.MeetingMapper;
import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingRoom;
import com.icss.oa.system.pojo.Employee;

public class TestMeetingMapper {

	// 获得spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 获得mapper对象(dao对象）
	MeetingMapper mapper = context.getBean(MeetingMapper.class);

	@Test
	// 测试插入操作
	public void testInsert() throws ParseException {
		Employee promoter = new Employee();
		promoter.setEmpId(4);

		MeetingRoom meetingRoom = new MeetingRoom();
		meetingRoom.setMeetingRoomId(4);
		// 创建pojo对象
		Meeting meeting = new Meeting(inform("2019-03-20 9:35:22"), new Date(), promoter, "111 ", "1111 ", meetingRoom);
		// 调用数据
		mapper.insert(meeting);

	}

	public Date inform(String str) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse(str);
		return date;
	}

	// 测试删除操作
	@Test
	public void testdelete() {
		// 调用数据
		mapper.delete(13);

	}

	// 测试查询会议数量操作
	@Test
	public void testGetmeetingCount() {
		// 调用数据
		Integer m = mapper.getMeetingCount();
		System.out.println("记录数是：" + m);

	}

	// 测试修改会议操作
	@Test
	public void testUpdate() throws Exception {
		Employee promoter = new Employee();
		promoter.setEmpId(10);

		MeetingRoom meetingRoom = new MeetingRoom();
		meetingRoom.setMeetingRoomId(6);
		// 创建pojo对象
		Meeting meeting = new Meeting(14, inform("2019-03-20 9:35:22"), new Date(), promoter, " ", " ", meetingRoom);
		// 调用数据
		mapper.update(meeting);

	}

	// 测试修改会议状态操作
	@Test
	public void testUpdateMeetingState() {

		// 调用数据
		mapper.updateMeetingState(2, "未审批");

	}

	// 测试会议单条查询操作
	@Test
	public void testQueryById() {
		// 数据查询操作
		Meeting meeting = mapper.queryById(1);
		System.out.println(meeting);
	}

	// 测试所有会议打印操作
	@Test
	public void testQuery() {
		List<Meeting> list = mapper.query();
		for (Meeting meeting : list) {
			System.out.println(meeting);

		}

	}

	// 测试会议分页查询
	@Test
	public void testQueryByPage() {

		HashMap<String, Integer> map = new HashMap<>();
		map.put("start", 0);
		map.put("pageSize", 5);

		List<Meeting> list = mapper.queryByPage(map);

		for (Meeting meeting : list) {
			System.out.println(meeting);
		}

	}

	// 测试会议分页查询2
	@Test
	public void testQueryByPage2() {

		List<Meeting> list = mapper.queryByPage2(0, 10);

		for (Meeting meeting : list) {
			System.out.println(meeting);
		}

	}

	// 测试动态查询
	@Test
	public void testQueryByCondition() {

		List<Meeting> list = mapper.queryByCondition(0,null, 5, null, "", "", "", "", "", "");

		for (Meeting meeting : list) {
			System.out.println(meeting);
		}
	}

	// 测试条件查询会议数量操作
	@Test
	public void testGetmeetingConditionCount() {
		// 调用数据
		Integer m = mapper.getMeetingConditionCount(0,null, null, null, null, null, null, null);
		System.out.println("记录数是：" + m);

	}

}
