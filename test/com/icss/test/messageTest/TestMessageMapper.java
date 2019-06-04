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
	//根据id查询信息
	public void testQueryById() {
		
		Message mes = mapper.queryById(11);
		
		System.out.println(mes);
	}

	@Test
	//插入
	public void testInsert() throws ParseException {
		
		Date date = new Date(); //获得当前系统的时间
		
		Employee mesSender = new Employee();
		mesSender.setEmpId(11);
		
		Employee mesReciver = new Employee();
		mesReciver.setEmpId(3);
		
		Message mes = new Message("ninijjj", "已发", "已读", date, "jjjjj",mesSender,mesReciver);
		
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
	//全部的模糊查询
	public void testQueryByCondition() {
		
		List<Message> list = mapper.queryByCondition(0, 2, "1998-02-19%", "44", "n");
		
		for (Message mes : list) {
			System.out.println(mes);
		}
	}
	
}
