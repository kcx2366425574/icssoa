package com.icss.test.messageTest;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	//根据id查询信息
	public void testQueryMesById() {
		
		Message mes = service.queryMesById(11);
		
		System.out.println(mes);
	}
	
	@Test
	//添加信息
	public void testAddMes() throws ParseException {
		
		Date date = new Date(); //获得当前系统的时间
		
		Employee mesSender = new Employee();
		mesSender.setEmpId(1);
		
		Employee mesReciver = new Employee();
		mesReciver.setEmpId(2);
		
		Message mes = new Message("nini", "已发", "已读", date, "jjjjj",mesSender,mesReciver);
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

}
