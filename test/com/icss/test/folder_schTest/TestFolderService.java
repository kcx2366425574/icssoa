package com.icss.test.folder_schTest;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.common.Pager;
import com.icss.oa.folder.pojo.Folder;
import com.icss.oa.folder.service.FolderService;
import com.icss.oa.system.pojo.Employee;

public class TestFolderService {
	ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
	FolderService service=context.getBean(FolderService.class);
	@Test
	public void testAddFolder()
	{
		Employee emp=new Employee();
		emp.setEmpId(5);
	  	Folder f=new Folder();
    	f.setFolId(2);
		Folder fol=new Folder("你好啊", 500.00, f, "啊啊啊，这是肿木了", "我靠", Date.valueOf("2011-10-01"), Date.valueOf("2014-10-11"), emp);
		service.addFolder(fol);
	}
	@Test
	public void testDeleteFolder()
	{
		service.deleteFolder(6);
	}
	@Test
	public void testUpdateFolder()
	{
		Employee emp=new Employee();
		emp.setEmpId(3);
	  	Folder f=new Folder();
    	f.setFolId(2);
		Folder fol=new Folder("智障", 705.00, f, "大撒币", "惊怕", Date.valueOf("2012-10-01"), Date.valueOf("2016-10-11"), emp);
		service.updateFolder(fol);
	}
	@Test
	public void testQueryFolderById()
	{
		System.out.println(service.queryFolderById(6));
	}
	@Test
	public void testQueryFolderByPage()
	{
		Pager pager=new Pager(service.getCount(), 10, 10);
		List<Folder> list=service.queryFolderByPage(pager);
		for(Folder folder:list)
		{
			System.out.println(folder);
		}
	}
	@Test 
	public void testGetCount()
	{
		System.out.println(service.getCount());
	}
	@Test
	public void testQueryByCondition()
	{
		Pager pager=new Pager(service.getCount(), 6, 2);
		List<Folder> list=service.queryByConditon(pager,2,3, "神魔");
		for(Folder folder:list)
		{
			System.out.println(folder);
		}
	}
	@Test
	public void testGetCountByCondition()
	{
		System.out.println(service.getCountByCondition(2,5, "a"));
	}
	@Test
  	public void testqueryByIds()
  	{
  		Integer[] ids = {2,3,4};
  		List<Folder> list=service.queryByIds(ids);
  		for (Folder folder : list) {
			System.out.println(folder);
		}
  	}
}
