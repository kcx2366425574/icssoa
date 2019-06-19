package com.icss.test.employeeTest;


/**
 * 对employee dao层进行测试
 * 增删改查
 * @author kcx
 */
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.icss.oa.system.dao.EmployeeMapper;
import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.pojo.Job;


public class TestEmployeeMapper {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	EmployeeMapper mapper = context.getBean(EmployeeMapper.class);

	@Test
	/**
	 * 测试插入员工数据
	 * @author kcx
	 */
	public void testInsert() {

		Department dept = new Department();
		dept.setDeptId(1);

		Job job = new Job();
		job.setJobId(1);

		Employee emp = new Employee("aaaa", "zhang", "123", "jjjj", java.sql.Date.valueOf("1999-1-1"), "11111111111", 100, "4444",
				"dddd", dept, job);
		mapper.insert(emp);
	}



    @Test
    /**
	 * 测试删除员工
	 * @author kcx
	 */
    public void testDelete(){
    	mapper.delete(16);
    }
	
    @Test
    public void queryById(){
    	System.out.println(mapper.queryById(1));
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
		Employee emp=new Employee(16,"测试", "ceshi", "123456", "男",java.sql.Date.valueOf("1878-03-12"), "12345678987", 7000, "ceshi@icss.com", "我会java", dept, job);
		mapper.update(emp);
	}

	
	@Test
	public void getAll(){
		List<Employee> list = mapper.queryByNothing();
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}
	
	@Test
	//instr
	public void testQueryByCondition() {

		List<Employee> list = mapper.queryByCondition(0, 10, null,"女", null,null);
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}
	
	
	@Test
	//时间查询
	public void testIdByBirthday() {
		SimpleDateFormat format = new SimpleDateFormat("MM-dd");
		String formatStr =format.format(new Date());
		Integer[] empId = mapper.queryByBirthday("%"+formatStr);
		for (int i : empId) {
			System.out.print(i+" ");
		}
	}
	
	@Test
	public void testGetCountByCondition() {
		int count = mapper.getCountByCondition(null, null, null,"李");
		System.out.println(count);
	}
	
	
	
	@Test
	public void testQueryLoginName(){
		Employee emp = mapper.queryByLoginName("lisi");
		System.out.println(emp);
	}

}
