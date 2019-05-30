package com.icss.test.folder_schTest;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.icss.oa.folder.dao.FolderMapper;
import com.icss.oa.folder.pojo.Folder;
import com.icss.oa.system.pojo.Employee;

public class TestFolderMapper {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	//Mapper Object
	    FolderMapper mapper=context.getBean(FolderMapper.class)	;
	    
	    @Test
	    public void testInsert()
	    {
	    	Employee emp=new Employee();
	    	emp.setEmpId(4);
	    	Folder f=new Folder();
	    	f.setFolId(2);
	    	Folder fol=new Folder("兵王", 500.00, f, "当兵的人", "军旅人生", Date.valueOf("2011-10-01"), Date.valueOf("2014-10-11"), emp);
	    	mapper.insert(fol);
	    	
	    }
	    @Test
	    public void testDelete()
	    {
	    	mapper.delete(7);
	    }
	    @Test
	    public void testupdate()
	    {
	    	Employee employee=new Employee();
	    	employee.setEmpId(5);
	    	Folder f=new Folder();
	    	f.setFolId(3);
	    	Folder folder=new Folder(9, "哈哈", 800.00, f, "你猜不到的", "我艹，这里有惊天秘密", Date.valueOf("2012-12-21"), Date.valueOf("2013-02-14"), employee);
	    	mapper.update(folder);
	    }
	    @Test
	    public void testQueryById()
	    {
	    	Folder fold=mapper.queryById(2);
	    	System.out.println(fold);
	    }
	    @Test
	    public void testQueryByPage()
	    {
	    	List<Folder> list=mapper.queryByPage(2, 5);
	    	for(Folder fol:list)
	    	{
	    		System.out.println(fol);
	    	}
	    }
	    @Test
	    public void testgetCount()
	    {
	    	int count=mapper.getCount();
	    	System.out.println(count);
	    }
	    @Test
	    public void testQueryByCondition()
	    {
	    	List<Folder> list=mapper.queryByCondition(1, 6,3, 3, "html");
	    	for(Folder folder:list)
	    	{
	    		System.out.println(folder);
	    	}
	    }
	    @Test
	    public void testGetCountByCondition()
	    {
	    	
	    	System.out.println(mapper.getCountByCondition(2,3, "上海"));
	    }
	    @Test
	    public void testQueryByIds() {		
			
			Integer[] ids = {2,3,5};
			
			List<Folder> list = mapper.queryByIds(ids);
			
			for (Folder fol : list) {
				System.out.println(fol);
			}
			
		}

}
