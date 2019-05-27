package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.file.dao.FileMapper;
import com.icss.oa.file.pojo.File;

public class TestFile {

	@Test
	public void test(){
		String str=new String();
		System.out.println(str+"a");
		System.out.println(1);
	}
	
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	FileMapper mapper = context.getBean(FileMapper.class);
	
	@Test
	public void testGetCount(){
		int count=mapper.getCountByCondition(1, "doc");
		System.out.println(count);
	}
}
