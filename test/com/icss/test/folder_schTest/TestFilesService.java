package com.icss.test.folder_schTest;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.common.FileHelper;
import com.icss.oa.common.Pager;
import com.icss.oa.folder.dao.FilesMapper;
import com.icss.oa.folder.pojo.Files;
import com.icss.oa.folder.pojo.Folder;
import com.icss.oa.folder.service.FilesService;
import com.icss.oa.system.pojo.Employee;

public class TestFilesService {
	ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
	FilesService service = context.getBean(FilesService.class);
	@Test
	public void testAddFolder() throws IOException
	{
	  	Folder f=new Folder();
    	f.setFolId(2);
		FileHelper helper=new FileHelper();
		Files file=new Files("haha", 500, f, "中心",Date.valueOf("2011-10-14"),Date.valueOf("2012-05-10"),helper.getContent("E:\\new\\fuck.jpg"));
		service.addFiles(file);
	}
	@Test
	public void testDeleteFiles()
	{
		service.deleteFiles(6);
	}
	@Test
	public void testUpdateFiles() throws IOException
	{
		FileHelper helper=new FileHelper();
	  	Folder f=new Folder();
    	f.setFolId(2);
    	Files file=new Files("haha", 500, f, "惊涛骇浪",Date.valueOf("2011-10-14"),Date.valueOf("2012-05-10"),helper.getContent("E:\\new\\fuck.jpg"));
		service.updateFiles(file);
	}
	@Test
	public void testQueryFilesById()
	{
		System.out.println(service.queryFileById(1));
	}
	@Test
	public void testQueryFilesByPage()
	{
		Pager pager=new Pager(service.getCount(), 10, 10);
		List<Files> list=service.queryFileByPage(pager);
		for(Files files:list)
		{
			System.out.println(files);
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
		List<Files> list=service.queryByConditon(pager,3, "s");
		for(Files files:list)
		{
			System.out.println(files);
		}
	}
	@Test
	public void testGetCountByCondition()
	{
		System.out.println(service.getCountByCondition(2, "a"));
	}
	@Test
  	public void testqueryByIds()
  	{
  		Integer[] ids = {2,3,4};
  		List<Files> list=service.queryByIds(ids);
  		for (Files file : list) {
			System.out.println(file);
		}
  	}
}
