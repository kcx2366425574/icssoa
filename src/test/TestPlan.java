package test;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.plan.dao.PlanMapper;
import com.icss.oa.plan.pojo.Plan;
import com.icss.oa.system.pojo.Department;
/**
 * test plan 
 * @time 2019-05-22
 * @author pk
 *
 */
public class TestPlan {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	PlanMapper mapper = context.getBean(PlanMapper.class);

	@Test
	//插入plan
	public void testInsert() {
		Department dept = new Department();
		dept.setDeptId(3);
		Plan plan = new Plan("星期四的计划", Date.valueOf("2019-03-22"), "写完测试类", dept);
        mapper.insert(plan);
	}
	
	@Test
	//查询时间，将年月日封装成一个string
	public void testTime(){
		List<Plan> list=mapper.queryByCondition("%05%","计划","技术部");
        for (Plan plan : list) {
			System.out.println(plan);
		}
	}
	
	@Test 
	//删除plan
	public void testDelete(){
		mapper.delete("星期一的计划");
	}

	@Test
	public void testUpdate(){
		Department dept=new Department();
		dept.setDeptId(3);
		Plan plan=new Plan(10,"html", Date.valueOf("2019-08-12"), "写完html，完全不慌", dept);
		mapper.update(plan);
	}
	
}
