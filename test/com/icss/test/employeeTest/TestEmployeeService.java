package com.icss.test.employeeTest;

import java.io.IOException;
/**
 * 对employee service层进行测试
 * 增删改查
 * @author kcx
 */
import java.sql.Date;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.common.Pager;
import com.icss.oa.message.service.MessageService;
import com.icss.oa.system.index.EmpIndexDao;
import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.pojo.Job;
import com.icss.oa.system.service.EmployeeService;

public class TestEmployeeService {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	EmployeeService service = context.getBean(EmployeeService.class);
	
	EmpIndexDao indexDao = context.getBean(EmpIndexDao.class);
	
	MessageService mesService = context.getBean(MessageService.class);
	
	@Test
	/**
	 * 测试插入员工数据
	 * @author kcx
	 */
	public void testInsert(){
		
		Department dept = new Department();
		dept.setDeptId(1);

		Job job = new Job();
		job.setJobId(1);
		Employee emp=new Employee("测试", "ceshi", "123456", "男", Date.valueOf("1878-03-12"), "12345678987", 3555, "ceshi@icss.com", "啥也不会", dept, job);
		service.addEmployee(emp);
		
	}
	
	@Test
	public void testGetId(){
		System.out.println(service.getId("lvbu"));
	}
	
	@Test
	/**
	 * 测试修改员工数据
	 * @author kcx
	 */
	public void testUpdate(){
		Department dept = new Department();
		dept.setDeptId(1);

		Job job = new Job();
		job.setJobId(1);
		Employee emp=new Employee(16,"测试aaaa", "ceshi", "123456", "男", Date.valueOf("1878-03-12"), "12345678987", 7000, "ceshi@icss.com", "我会java", dept, job);
		service.updateEmployee(emp);
	}
	
	
	@Test
	/**
	 * 测试删除员工
	 * @author kcx
	 */
	public void testDelete(){
		service.deleteEmployee(16);
	}
	
	@Test
	/**
	 * 测试查询员工数据
	 * @author kcx
	 */
	public void testQueryByCondition(){
		Pager pager = new Pager(service.getEmpCount(null,"男", null,null), 1,10);
		List<Employee> list =service.queryEmployee(pager, null,"男", null,null);
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}
	
	@Test
	//测试通过员工登录名查询数据
	public void testQueryByLoginName(){
		Employee emp = service.queryEmpByLoginName("lisi");
		System.out.println(emp);
	}
	
	
	@Test
	//测试通过员工登录名查询数据
	public void testHappyBirthday(){
		service.happyBirthday();
	}
	
	@Test
	public void getAll(){
		List<Employee> list = service.queryByNothing();
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}
	
	@Test
	public void updatePwd(){
		service.updatePwd("lvbu", "111111");
	}
	
	
	
	/**
	 * 重建员工的索引
	 */
	@Test
	public void testCreateIndex(){
		
		
		List<Employee> list = service.queryByNothing();
		
		for (Employee emp : list) {
						
			System.out.println(emp);
			
			try {
				/********** 生成索引 *************/
				// 创建索引文档
				Document document = new Document();
				document.add(new TextField("empId", String.valueOf(emp.getEmpId())  , Store.YES));
				document.add(new TextField("empName", emp.getEmpName(), Store.YES));
				document.add(new TextField("empPhone", emp.getEmpPhone(), Store.YES));
				document.add(new TextField("empInfo", emp.getEmpInfo(), Store.YES));

				// 调用索引dao
				indexDao.create(document);
				
				System.out.println("索引已创建");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}	
		
	}
	
	@Test
	public void updateHead(){
		service.updateHead("lisi", "abcd");
	}
	
	@Test
	public void queryHead(){
		String head = service.queryHead("lisi");
		System.out.println(head);
	}
}
