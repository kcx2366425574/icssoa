package com.icss.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.FileCopyUtils;
import com.icss.oa.pic.dao.PicMapper;
import com.icss.oa.pic.pojo.Pic;

/**
 * ͼƬdao������
 * 
 * @author bhl
 *
 */

public class TestPicMapper {

	// ���Spring������������
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// ���Mapper����(dao����
	PicMapper mapper = context.getBean(PicMapper.class);

	@Test
	public void testInsert() throws IOException {

		FileInputStream fis = new FileInputStream("e:\\֣ˬ.jpg");
		byte[] picEmp = FileCopyUtils.copyToByteArray(fis);

		Pic pic = new Pic("֣ˬ", 132497L, "С��Ů", new Date(), "wo", picEmp);
		mapper.insert(pic);
	}

	@Test
	public void testQueryById() throws IOException {

		Pic pic = mapper.queryById(2);
		System.out.println(pic);

		FileOutputStream fos = new FileOutputStream("e:\\" + pic.getPicName());
		FileCopyUtils.copy(pic.getPicData(), fos);
		fos.close();
	}

	@Test
	public void testQuery() throws IOException {

		List<Pic> list = mapper.query();

		for (Pic pic : list) {

			System.out.println(pic);

			FileOutputStream fos = new FileOutputStream("e:\\" + pic.getPicName());
			FileCopyUtils.copy(pic.getPicData(), fos);
			fos.close();
		}
	}

	@Test
	public void testDelete() {
		mapper.delete(1);
	}

}
