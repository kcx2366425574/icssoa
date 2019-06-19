package com.icss.test.messageTest;

import java.util.Date;
import java.io.IOException;
import java.util.List;
import java.text.SimpleDateFormat;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.queryparser.classic.ParseException;
import com.icss.oa.common.Pager;
import com.icss.oa.message.index.MessageIndexDao;
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

	MessageIndexDao indexDao = context.getBean(MessageIndexDao.class);

	/**
	 * 日期转换
	 * 
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public Date inform(String str) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		try {
			date = format.parse(str);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 根据id查询信息
	 */
	@Test
	public void testQueryMesById() {

		Message mes = service.queryMesById(11);

		System.out.println(mes);
	}

	/**
	 * 添加信息
	 * 
	 * @throws ParseException
	 */
	@Test
	public void testAddMes() throws ParseException {

		Date date = new Date(); // 获得当前系统的时间

		Employee mesSender = new Employee();
		mesSender.setEmpId(6);

		Employee mesReciver = new Employee();
		mesReciver.setEmpId(2);

		Message mes = new Message("nini", "已发", "已读", date, "jjjjj", mesSender, mesReciver);
		service.addMes(mes);
	}

	/**
	 * 修改信息
	 * 
	 * @throws ParseException
	 */
	@Test
	public void testUpdateMes() throws ParseException {

		Employee mesSender = new Employee();
		mesSender.setEmpId(1);

		Employee mesReciver = new Employee();
		mesReciver.setEmpId(2);

		Message mes = new Message(11, "n", "已发", "已读", inform("1998-2-19 12:12:59"), "jjjjj", mesSender, mesReciver);
		service.updateMes(mes);
	}

	/**
	 * 删除信息
	 */
	@Test
	public void testDeleteMes() {
		service.deleteMes(12);
	}

	/**
	 * 全部的模糊查询
	 */
	@Test
	public void testQueryMesByCondition() {

		// Employee mesReciver = new Employee();
		// mesReciver.setEmpId(12);
		//
		Pager pager = new Pager();

		pager.setStart(0);
		pager.setPageSize(2);

		List<Message> list = service.queryMesByCondition(pager, null, null, null, null, "放假");

		for (Message mes : list) {
			System.out.println(mes);
		}
	}

	/**
	 * 全部的模糊查询 没有收件人
	 */
	@Test
	public void testQueryMesByCondition1() {

		Pager pager = new Pager();

		pager.setStart(0);
		pager.setPageSize(2);

		List<Message> list = service.queryMesByCondition1(pager, "1998-02-19%", "zhang", "n");

		for (Message mes : list) {
			System.out.println(mes);
		}
	}

	/**
	 * 根据条件获得总记录数
	 */
	@Test
	public void testGetMesCountByCondition() {

		// Employee mesReciver = new Employee();
		//
		// mesReciver.setEmpId(12);
		//
		int count = service.getMesCountByCondition(null, null, null, null, "放假");
		System.out.println(count);
	}

	/**
	 * 重建信息的索引
	 */
	@Test
	public void testCreateIndex() {

		Pager pager = new Pager(service.getMesCount(), service.getMesCount(), 1);
		List<Message> list = service.queryMesByCondition(pager, null, null, null, null, null);

		for (Message mes : list) {

			System.out.println(mes);

			try {
				/********** 生成索引 *************/
				// 创建索引文档
				Document document = new Document();
				document.add(new TextField("mesId", String.valueOf(mes.getMesId()), Store.YES));
				document.add(new TextField("mesTitle", mes.getMesTitle(), Store.YES));
				document.add(new TextField("mesInfo", mes.getMesInfo(), Store.YES));

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
	 * 
	 * @throws InvalidTokenOffsetsException
	 * @throws IOException
	 * @throws ParseException
	 *
	 */
	@Test
	public void testQueryByIndex() throws IOException, InvalidTokenOffsetsException, ParseException {

		List<Message> list = service.queryMesByIndex("哈哈");

		for (Message mes : list) {
			System.out.println(mes);
		}

	}

	/**
	 * 根据登录名查询信息
	 */
	@Test
	public void testQueryMesByLoginName() {

		Pager pager = new Pager();

		pager.setStart(0);
		pager.setPageSize(2);

		List<Message> list = service.queryMesByLoginName("zhaoyi", pager, "1998-02-19%", null, null, null, null);

		for (Message mes : list) {
			System.out.println(mes);
		}
	}

	/**
	 * 根据登录名获得总记录数
	 */
	@Test
	public void testGetMesCountByEmpLoginName() {
		int count = service.getMesCountByEmpLoginName("zhaoyi", "1998-02-19%", null, null, null, null);
		System.out.println(count);
	}

	@Test
	/**
	 * 草稿箱和发件箱
	 */
	public void testQueryMesDraft() {

		Pager pager = new Pager();
		pager.setStart(0);
		pager.setPageSize(2);

		List<Message> list = service.queryMesDraft("未发", pager, "zhaoyi");

		for (Message mes : list) {
			System.out.println(mes);
		}

	}

	/**
	 * 草稿箱和发件箱个数
	 */
	@Test
	public void testGetMesDraftCount() {
		int count = service.getMesDraftCount("未发", "zhaoyi");
		System.out.println(count);
	}

	@Test
	/**
	 * 收件箱
	 */
	public void testQueryMesInbox() {

		Pager pager = new Pager();
		pager.setStart(0);
		pager.setPageSize(2);

		List<Message> list = service.queryMesInbox(pager, "zhaoyi");

		for (Message mes : list) {
			System.out.println(mes);
		}

	}

	/**
	 * 收件箱个数
	 */
	@Test
	public void testGetMesInboxCount() {
		int count = service.getMesInboxCount("zhaoyi");
		System.out.println(count);
	}

	@Test
	/**
	 * 未读消息
	 */
	public void testQueryMesUnread() {

		Pager pager = new Pager();
		pager.setStart(0);
		pager.setPageSize(2);

		List<Message> list = service.queryMesUnread("已发", "未读", pager, "zhaoyi");

		for (Message mes : list) {
			System.out.println(mes);
		}

	}

	/**
	 * 未读消息个数
	 */
	@Test
	public void testGetMesUnreadCount() {
		int count = service.getMesUnreadCount("已发", "未读", "zhaoyi");
		System.out.println(count);
	}
	
	/**
	 * 群发消息
	 */
	@Test
	public void testGroupSend() {

		Integer[] ids = { 1, 2 };

		Date date = new Date(); // 获得当前系统的时间

		Employee mesSender = new Employee();
		mesSender.setEmpId(6);

		Message mes = new Message("测试", "已发", "已读", date, "ceshi", mesSender, null);
		service.groupSend(mes, ids,"lisi");
	}

}
