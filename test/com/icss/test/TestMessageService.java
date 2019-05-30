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
	//全部的模糊查询
	public void testQueryMesByCondition() {
		
		List<Message> list = service.queryMesByCondition(0, 2, "1998-02-19%", "44", "n");
		
		for (Message mes : list) {
			System.out.println(mes);
		}
	}
	
}
