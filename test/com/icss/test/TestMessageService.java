package com.icss.test;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.icss.oa.message.pojo.Message;
import com.icss.oa.message.service.MessageService;
import com.icss.oa.system.pojo.Employee;

/**
 * 在线信息业务测试类
 * 
 * @author bhl
 *
 */
public class TestMessageService {

	// 获得Spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 获得Service对象
	MessageService service = context.getBean(MessageService.class);
	
	//日期转换
	public Date inform(String str) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse(str);
		return date;
	}
	
	@Test
	//添加信息
	public void testAddMes() throws ParseException {
		
		Employee mesSender = new Employee();
		mesSender.setEmpId(1);
		
		Employee mesReciver = new Employee();
		mesReciver.setEmpId(2);
		
		Message mes = new Message("nini", "已发", "已读", inform("1998-2-19 12:12:59"), "jjjjj",mesSender,mesReciver);
		service.addMes(mes);
	}
	
	@Test
	//修改信息
	public void testUpdateMes() throws ParseException {
		
		Employee mesSender = new Employee();
		mesSender.setEmpId(1);
		
		Employee mesReciver = new Employee();
		mesReciver.setEmpId(2);
		
		Message mes = new Message(11, "n",  "已发", "已读", inform("1998-2-19 12:12:59"), "jjjjj",mesSender,mesReciver);
		service.updateMes(mes);
	}

	@Test
	//删除信息
	public void testDeleteMes() {
		service.deleteMes(12);
	}

	@Test
	//根据id查询信息
	public void testQueryMesById() {
		Message mes = service.queryMesById(11);
		System.out.println(mes);
	}

	@Test
	//查询全部信息，然后进行分页
	public void testQueryMesByPage() {
		
		HashMap<String, Integer> map = new HashMap<>();
		map.put("start", 0);
		map.put("pageSize", 2);

		List<Message> list = service.queryMesByPage(map);

		for (Message mes : list) {
			System.out.println(mes);
		}
	}
	
	@Test
	//根据发送时间查询信息
	public void testQueryMesSendDate() throws ParseException {
		List<Message> list = service.queryMesSendDate(inform("1998-2-19 00:00:00"));
		
		for(Message mes : list) {
			System.out.println(mes);
		}
	}
	
	@Test
	//根据收件人邮箱查询信息
	public void queryByReciverEmail() {
		
		List<Message> list = service.queryByReciverEmail("44441");
		
		for(Message mes : list) {
			System.out.println(mes);
		}
	}
	
	@Test
	//根据发送时间进行模糊查询
	public void testQueryByDateCondition() {
		
		List<Message> list = service.queryByDateCondition(0, 1, "1998-02-19%");

		for (Message mes : list) {
			System.out.println(mes);
		}
	}
	
	@Test
	//根据收件人邮箱进行模糊查询
	public void testQueryByReciverCondition() {
		
		List<Message> list = service.queryByReciverCondition(0, 1, "44");
		
		for (Message mes : list) {
			System.out.println(mes);
		}
	}
	
	@Test
	//根据题目进行模糊查询
	public void testQueryByTitleCondition() {

		List<Message> list = service.queryByTitleCondition(0, 3, "n");

		for (Message mes : list) {
			System.out.println(mes);
		}
	}

}
