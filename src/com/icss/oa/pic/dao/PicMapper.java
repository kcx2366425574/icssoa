package com.icss.oa.pic.dao;

import java.util.List;
import com.icss.oa.pic.pojo.Pic;

/**
 * ͼƬdao��
 * @author bhl
 *
 */
public interface PicMapper {

	void insert(Pic pic);
	
	void delete(Integer picId);
	
	Pic queryById(Integer picId);
	
	List<Pic> query();
	
}