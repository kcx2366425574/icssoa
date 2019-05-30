package com.icss.oa.system.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.icss.oa.system.pojo.Notice;

public interface NoticeMapper {
	void insert(Notice notice);
	void update(Notice notice);
	void delete(int id);
	Notice queryById(int id);
	Notice queryByName(String name);
	int getCount();
	List<Notice> query(@Param("start") Integer start,@Param("pageSize") Integer pageSize);
}
