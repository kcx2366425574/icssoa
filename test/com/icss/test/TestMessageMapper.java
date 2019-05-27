package com.icss.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.icss.oa.message.dao.MessageMapper;
import com.icss.oa.message.pojo.Message;
import com.icss.oa.system.pojo.Employee;

/**
 * 在线信息测试类
 * @author bhl
 *
 */
public class TestMessageMapper {

	// 获得Spring容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 获得Mapper对象(dao对象）
	MessageMapper mapper = context.getBean(MessageMapper.class);
	
	//日期转换
	public Date inform(String str) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse(str);
		return date;
	}

	@Test
	//插入
	public void testInsert() throws ParseException {
		
		Employee mesSender = new Employee();
		mesSender.setEmpId(11);
		
		Employee mesReciver = new Employee();
		mesReciver.setEmpId(3);
		
		Message mes = new Message("ninijjj", "已发", "已读", inform("1998-2-19 12:12:59"), "jjjjj",mesSender,mesReciver);
		
		mapper.insert(mes);
	}

	@Test
	//修改
	public void update() throws ParseException {
		
		Employee mesSender = new Employee();
		mesSender.setEmpId(1);
		
		Employee mesReciver = new Employee();
		mesReciver.setEmpId(2);
		
		Message mes = new Message(11, "n", "已发", "已读", inform("1998-2-19 12:12:59"), "jjjjj",mesSender,mesReciver);
		mapper.update(mes);
	}

	@Test
	//删除
	public void testDelete() {
		mapper.delete(13);
	}

	@Test
	//根据id进行查询
	public void testQueryById() {
		Message mes = mapper.queryById(11);
		System.out.println(mes);
	}

	@Test
	//全部查询然后进行分页
	public void testQueryByPage() {
		
		HashMap<String, Integer> map = new HashMap<>();
		map.put("start", 0);
		map.put("pageSize", 2);

		List<Message> list = mapper.queryByPage(map);

		for (Message mes : list) {
			System.out.println(mes);
		}
	}
	
	@Test
	//根据发送时间进行查询
	public void testQueryBySendDate() throws ParseException {

		List<Message> list = mapper.queryBySendDate(inform("1998-2-19 00:00:00"));

		for (Message mes : list) {
			System.out.println(mes);
		}
	}
	
	@Test
	//根据收件人邮箱进行查询
	public void queryByReciverEmail() {

		List<Message> list = mapper.queryByReciverEmail("44441");

		for (Message mes : list) {
			System.out.println(mes);
		}
	}
	
	@Test
	//根据发送时间进行模糊查询
	public void testQueryByDateCondition() {

		List<Message> list = mapper.queryByDateCondition(0, 3, "1998-02-19%");

		for (Message mes : list) {
			System.out.println(mes);
		}
	}
	
	@Test
	//根据收件人邮箱进行模糊查询
	public void testQueryByReciverCondition() {

		List<Message> list = mapper.queryByReciverCondition(0,1,"44");

		for (Message mes : list) {
			System.out.println(mes);
		}
	}
	
	@Test
	//根据题目进行模糊查询
	public void testQueryByTitleCondition() {

		List<Message> list = mapper.queryByTitleCondition(0, 3, "ni");

		for (Message mes : list) {
			System.out.println(mes);
		}
	}
	
	@Test
	//查询全部
	public void testQuery() {
		
		List<Message> list = mapper.query();
		
		for(Message mes : list) {
			System.out.println(mes);
		}
	}
	
}
