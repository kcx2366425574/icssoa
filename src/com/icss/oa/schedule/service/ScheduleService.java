package com.icss.oa.schedule.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.schedule.dao.ScheduleMapper;
import com.icss.oa.schedule.index.SchIndexDao;
import com.icss.oa.schedule.pojo.Schedule;
import com.icss.oa.system.pojo.Employee;


@Service
@Transactional(rollbackFor=Exception.class)
public class ScheduleService {
	@Autowired
	private ScheduleMapper mapper;
	@Autowired
	private SchIndexDao indexDao;
	public void addSchedule(Schedule sch)
	{
		mapper.insert(sch);
		int schId = mapper.getLastInsertId();
		try {
			/********** 生成索引 *************/
			// 创建索引文档
			Document document = new Document();
			document.add(new TextField("schId", String.valueOf(schId), Store.YES));
			document.add(new TextField("schName", sch.getSchName(), Store.YES));
			document.add(new TextField("schInfo", sch.getSchInfo(), Store.YES));

			// 调用索引dao
			indexDao.create(document);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void deleteSchedule(Integer schId)
	{
		mapper.delete(schId);
		// 索引
		try {
			Term term = new Term("schId", String.valueOf(schId));
			// 调用索引dao
			indexDao.delete(term);
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
	public void updateSchedule(Schedule sch)
	{
		mapper.update(sch);
		// 索引
		try {
			Term term = new Term("schId", String.valueOf(sch.getSchId()));

			// 创建索引文档
			Document document = new Document();
			document.add(new TextField("schId", String.valueOf(sch.getSchId()), Store.YES));
			document.add(new TextField("schName", sch.getSchName(), Store.YES));	
			document.add(new TextField("schInfo", sch.getSchInfo(), Store.YES));
			// 调用索引dao
			indexDao.update(term, document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<Schedule> querySchedule()
	{
		return mapper.query();
	}
	public Schedule queryBySchId(Integer schId)
	{
		return mapper.queryById(schId);
	}
	public List<Schedule> queryByPage(Pager pager)
	{
		return mapper.queryByPage(pager.getStart(), pager.getPageSize());
	}
	public int getCount()
	{
		return mapper.getCount();
	}
	public List<Schedule> queryByCondition (Pager pager,@Param("empId")Integer empId1,@Param("empId")Integer empId2,@Param("schName")String schName)
	{
		return mapper.queryByCondition(pager.getStart(), pager.getPageSize(), empId1, empId2, schName);
	}
	public List<Schedule> queryByIds(@Param("ids") Integer[] ids)
	{
		return mapper.queryByIds(ids);
	}
	public int getCountByCondition(@Param("empId")Integer e1,@Param("empId")Integer e2,@Param("schName")String schName)
	{
		return mapper.getCountByCondition(e1, e2, schName);
	}
	/**
	 * 全文检索
	 * 
	 * @throws ParseException
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException 
	 */
	@Transactional(readOnly = true)
	public List<Schedule> querySchByIndex(String queryStr) throws ParseException, IOException, InvalidTokenOffsetsException {

		// 设置常见停用词（的，么，啊，着之类的东东）
		String[] self_stop_words = { "的", "着", "啊", "么", "嘛" , "是" };
		CharArraySet cas = new CharArraySet(Version.LUCENE_47, 0, true);
		for (int i = 0; i < self_stop_words.length; i++) {
			cas.add(self_stop_words[i]);
		}

		// 加入系统默认停用词
		Iterator<Object> itor = StandardAnalyzer.STOP_WORDS_SET.iterator();
		while (itor.hasNext()) {
			cas.add(itor.next());
		}

		// 中文分词器（设置停用词）
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47,cas);

		// 创建查询解析器对象，多字段搜索
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_47,
				new String[] { "schId", "schName",  "schInfo" }, analyzer);

		// 创建查询对象
		Query query = queryParser.parse(queryStr);

		// 调用索引dao
		List<Schedule> list = indexDao.search(query);

		return list;
	}
}
