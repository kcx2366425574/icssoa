package com.icss.test.messageTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.icss.oa.message.dao.MessageMapper;
import com.icss.oa.message.pojo.Message;
import com.icss.oa.system.pojo.Employee;

/**
 * 在线信息测试类
 * 
 * @author bhl
 *
 */
public class TestMessageMapper {

	// 获得Spring容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 获得Mapper对象(dao对象）
	MessageMapper mapper = context.getBean(MessageMapper.class);

	// 日期转换
	public Date inform(String str) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse(str);
		return date;
	}

	@Test
	// 根据id查询信息
	public void testQueryById() {

		Message mes = mapper.queryById(11);

		System.out.println(mes);
	}

	@Test
	// 插入
	public void testInsert() throws ParseException {

		Date date = new Date(); // 获得当前系统的时间

		Employee mesSender = new Employee();
		mesSender.setEmpId(11);

		Employee mesReciver = new Employee();
		mesReciver.setEmpId(3);

		Message mes = new Message("ninijjj", "已发", "已读", date, "jjjjj", mesSender, mesReciver);

		mapper.insert(mes);
	}

	@Test
	// 修改
	public void update() throws ParseException {

		Employee mesSender = new Employee();
		mesSender.setEmpId(1);

		Employee mesReciver = new Employee();
		mesReciver.setEmpId(2);

		Message mes = new Message(11, "n", "已发", "已读", inform("1998-2-19 12:12:59"), "jjjjj", mesSender, mesReciver);
		mapper.update(mes);
	}

	@Test
	// 删除
	public void testDelete() {
		mapper.delete(13);
	}

	@Test
	// 全部的模糊查询
	public void testQueryByCondition() {

		// Employee mesReciver = new Employee();
		// mesReciver.setEmpId(12);

		List<Message> list = mapper.queryByCondition(0, 2, null, null, null, null, "放假");

		for (Message mes : list) {
			System.out.println(mes);
		}
	}

	@Test
	// 全部的模糊查询
	public void testQueryByCondition1() {

		List<Message> list = mapper.queryByCondition1(0, 2, "1998-02-19%", "zhang", "n");

		for (Message mes : list) {
			System.out.println(mes);
		}
	}

	@Test
	// 根据条件获得总记录数
	public void testGetCountByCondition() {
		//
		// Employee mesReciver = new Employee();
		// mesReciver.setEmpId(12);

		int count = mapper.getCountByCondition(null, null, null, null, "放假");

		System.out.println(count);
	}

	@Test
	// 根据登录名查询对应的信息
	public void testQueryByLoginName() {

		List<Message> list = mapper.queryByLoginName("zhaoyi", 0, 7,null, null, null, null,null);

		for (Message mes : list) {
			System.out.println(mes);

		}
	}

	@Test
	// 根据登录名获得总记录数
	public void testGetCountByEmpLoginName() {

		int count = mapper.getCountByEmpLoginName("zhaoyi","1998-02-19%", null, null, null,null);
		System.out.println(count);
	}

	@Test
	// 查询草稿箱里面的邮件
	public void testQueryDraft() {

		List<Message> list = mapper.queryDraft("未发", 0, 2, "zhaoyi");

		for (Message mes : list) {
			System.out.println(mes);
		}

	}

	@Test
	// 查询草稿箱的个数
	public void testGetDraftCount() {

		int count = mapper.getCountDraft("未发", "zhaoyi");
		System.out.println(count);

	}

	@Test
	// 查询收件箱里面的邮件
	public void testQueryInbox() {

		List<Message> list = mapper.queryInbox(0, 2, "zhaoyi");

		for (Message mes : list) {
			System.out.println(mes);
		}

	}

	@Test
	// 查询收件箱的个数
	public void testGetInboxCount() {

		int count = mapper.getCountInbox("zhaoyi");
		System.out.println(count);

	}

	@Test
	// 查询未读消息里面的邮件
	public void testQueryUnread() {

		List<Message> list = mapper.queryUnread("已发", "未读", 0, 2, "zhangsan");
		
		for (Message mes : list) {
			System.out.println(mes);
		}

	}

	@Test
	// 查询未读消息的个数
	public void testGetUnreadCount() {

		int count = mapper.getCountUnread("已发", "未读", "zhaoyi");
		System.out.println(count);

	}
}
