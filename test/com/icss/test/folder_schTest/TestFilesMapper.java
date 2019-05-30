package com.icss.test.folder_schTest;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.common.FileHelper;
import com.icss.oa.folder.dao.FilesMapper;
import com.icss.oa.folder.pojo.Files;
import com.icss.oa.folder.pojo.Folder;

public class TestFilesMapper {
	// ���Spring������������
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	// Mapper Object
	FilesMapper mapper = context.getBean(FilesMapper.class);
	@Test
	public void testInsert() throws IOException
	{
		Folder folder=new Folder();
		folder.setFolId(1);
	//	Files files=new Files();
		FileHelper helper=new FileHelper();
		Files files=new Files("haha", 500, folder, "厚爱",Date.valueOf("2011-10-14"),Date.valueOf("2012-05-10"),helper.getContent("E:\\new\\fuck.jpg"));
		mapper.insert(files);
	}
	@Test
	public void testDelete()
	{
		mapper.delete(5);
	}
	@Test
	public void testUpdate()throws IOException
	{
		Folder folder=new Folder();
		folder.setFolId(6);
		FileHelper helper=new FileHelper();
		Files file=new Files(7, "haha", 500, folder, "中华",Date.valueOf("2011-10-14"),Date.valueOf("2012-05-10"),helper.getContent("E:\\new\\fuck.jpg"));
		mapper.update(file);
	}
	@Test
	public void testqueryById()
	{
		System.out.println(mapper.queryById(9));
	}
	@Test
    public void testQueryByPage()
    {
    	List<Files> list=mapper.queryByPage(2, 5);
    	for(Files files:list)
    	{
    		System.out.println(files);
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
    	List<Files> list=mapper.queryByCondition(1, 3, 3, "h");
    	for(Files files:list)
    	{
    		System.out.println(files);
    	}
    }
	@Test
    public void testGetCountByCondition()
    {
    	
    	System.out.println(mapper.getCountByCondition(2,"b"));
    }
	 @Test
	    public void testQueryByIds() {		
			
			Integer[] ids = {2,3,5};
			
			List<Files> list = mapper.queryByIds(ids);
			
			for (Files fil : list) {
				System.out.println(fil);
			}
			
		}
	

}
