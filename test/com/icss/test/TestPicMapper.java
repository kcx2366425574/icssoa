package com.icss.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.CopyUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.FileCopyUtils;

import com.icss.oa.pic.dao.PicMapper;
import com.icss.oa.pic.pojo.Pic;

/**
 * ͼƬdao������
 * 
 * @author Administrator
 *
 */
public class TestPicMapper {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	PicMapper mapper = context.getBean(PicMapper.class);

	@Test
	public void insert() throws IOException {

		//��������ȡ�ļ�
		FileInputStream fis = new FileInputStream("e:\\������.jpg");

		//ת��Ϊ�ֽ�����
		byte[] picEmp = FileCopyUtils.copyToByteArray(fis);

		Pic pic = new Pic("������.jpg", 132497L, "������������", new Date(), "zhangsan", picEmp);

		mapper.insert(pic);
	}

	@Test
	public void queryById() throws IOException {
		
		Pic pic = mapper.queryById(1);		
		
		System.out.println(pic);
		
		//�����
		FileOutputStream fos = new FileOutputStream("e:\\" + pic.getPicName());
		
		//�ֽ����鸴�Ƶ������
		FileCopyUtils.copy(pic.getPicData(), fos);
		
		fos.close();

	}

}