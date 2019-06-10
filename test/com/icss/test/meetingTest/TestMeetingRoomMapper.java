package com.icss.test.meetingTest;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.meeting.dao.MeetingRoomMapper;
import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingRoom;
import com.icss.oa.system.dao.DepartmentMapper;
import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.pojo.Employee;

/**
 * 测试会议室Mapper类
 * 
 * @author Administrator
 *
 */

public class TestMeetingRoomMapper {
	// 获得spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 获得mapper对象(dao对象）
	MeetingRoomMapper mapper = context.getBean(MeetingRoomMapper.class);

	@Test
	// 测试插入操作
	public void testInsert() {
		// 创建pojo对象
		MeetingRoom meetingRoom = new MeetingRoom("aaa", "aaa", "aaaa", 20, "aaa");
		// 调用数据
		mapper.insert(meetingRoom);

	}

	// 测试删除操作
	@Test
	public void testdelete() {
		// 调用数据
		mapper.delete(14);

	}

	// 测试会议室单条查询操作
	@Test
	public void testQueryById() {
		// 数据查询操作
		MeetingRoom meetingRoom = mapper.queryById(2);
		System.out.println(meetingRoom);
	}

	// 测试修改会议室操作
	@Test
	public void testUpdate() {

		// 创建pojo对象
		MeetingRoom meetingRoom = new MeetingRoom(15, "财政部", "财政办公室", "kkkkk", 44, "55555");
		// 调用数据
		mapper.update(meetingRoom);

	}

	// 测试修改会议室操作
	@Test
	public void testUpdateMeetingRoomState() {

		// 调用数据
		mapper.updateMeetingRoomState(2, "未预约");

	}

	// 测试所有会议室打印操作
	@Test
	public void testQuery() {
		List<MeetingRoom> list = mapper.query();
		for (MeetingRoom meetingRoom : list) {
			System.out.println(meetingRoom);
		}

	}

	// 测试会议室分页查询
	@Test
	public void testQueryByPage() {

		HashMap<String, Integer> map = new HashMap<>();
		map.put("start", 0);
		map.put("pageSize", 5);

		List<MeetingRoom> list = mapper.queryByPage(map);

		for (MeetingRoom meetingRoom : list) {
			System.out.println(meetingRoom);
		}

	}

	// 测试动态查询
	@Test
	public void testQueryByCondition() {

		List<MeetingRoom> list = mapper.queryByCondition(0, 5, null, "三楼", null, null, "有", 20);

		for (MeetingRoom meetingRoom : list) {
			System.out.println(meetingRoom);
		}
	}

	// 测试会议分页查询2
	@Test
	public void testQueryByPage2() {

		List<MeetingRoom> list = mapper.queryByPage2(0, 10);

		for (MeetingRoom meetingRoom : list) {
			System.out.println(meetingRoom);
		}

	}

	// 测试条件查询会议室数量操作
	@Test
	public void testGetmeetingRoomCount() {
		// 调用数据
		Integer m = mapper.getMeetingRoomConditionCount(null, "三楼", null, null, null, null);
		System.out.println("记录数是：" + m);

	}

	// 测试查询会议室数量操作
	@Test
	public void testGetmeetingCount() {
		// 调用数据
		Integer m = mapper.getMeetingRoomCount();
		System.out.println("记录数是：" + m);

	}
}
