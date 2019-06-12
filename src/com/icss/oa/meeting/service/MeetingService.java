package com.icss.oa.meeting.service;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.icss.oa.common.Pager;
import com.icss.oa.meeting.dao.MeetingMapper;
import com.icss.oa.meeting.index.MeetingIndexDao;
import com.icss.oa.meeting.index.MeetingRoomIndexDao;
import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingRoom;


/**
 * 会议业务
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)//加入事物
public class MeetingService {
	
	@Autowired
	private MeetingMapper mapper;
	
	@Autowired
	private MeetingIndexDao indexDao;
	
	//增加会议
	
	public void addMeeting(Meeting meeting){
		mapper.insert(meeting);
		
		int meetingId = mapper.getLastInsertId();
		
		try {
			/********** 生成索引 *************/
		    // 创建索引文档
			    Document document = new Document();
			document.add(new TextField("meetingId", String.valueOf(meetingId), Store.YES));
			document.add(new TextField("meetingTheme", meeting.getMeetingTheme(), Store.YES));
			document.add(new TextField("meetingState", meeting.getMeetingState(), Store.YES));
			

			// 调用索引dao
			indexDao.create(document);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 修改会议
	 */
	public void updateMeeting(Meeting meeting) {
		mapper.update(meeting);
		
		// 索引
		try {
			Term term = new Term("meetingId", String.valueOf(meeting.getMeetingId()));

			// 创建索引文档
			Document document = new Document();
			document.add(new TextField("meetingId", String.valueOf(meeting.getMeetingId()), Store.YES));
			document.add(new TextField("meetingTheme", meeting.getMeetingTheme(), Store.YES));
			document.add(new TextField("meetingState", meeting.getMeetingState(), Store.YES));
			

			// 调用索引dao
			indexDao.update(term, document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除会议
	 */
	public void deleteMeeting(Integer meetingId) {
		mapper.delete(meetingId);
		// 索引
		try {
			Term term = new Term("meetingId", String.valueOf(meetingId));
			// 调用索引dao
			indexDao.delete(term);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询会议数量
	 * @return 
	 */
	public int getMeetingCount() {
		return mapper.getMeetingCount();
	}
	
	/**
	 * 通过id查询会议
	 */
	@Transactional(readOnly=true)
	public Meeting queryMeetingById(Integer meetingId) {
		return mapper.queryById(meetingId);
	}
	
	/**
	 * 查询所有会议
	 */
	@Transactional(readOnly=true)
	public List<Meeting> queryMeeting() {
		return mapper.query();
	}
	
	/**
	 * 分页查询所有会议
	 */
	@Transactional(readOnly=true)
	public List<Meeting> queryMeetingByPage(Pager pager) {
		return mapper.queryByPage2(pager.getStart(),pager.getPageSize());
	}
	
	/**
	 * 条件查询所有会议
	 */
	@Transactional(readOnly=true)
	public List<Meeting> queryMeetingByCondition(Pager pager,Integer meetingId,Integer empId,String empName,String meetingState,String startTime,String meetingTheme, String meetingRoomName,String meetingRoomLocation) {
		return mapper.queryByCondition(pager.getStart(),pager.getPageSize(), meetingId, empId,empName, meetingState, startTime, meetingTheme, meetingRoomName, meetingRoomLocation);
	}
	
	/**
	 * 获得符合条件的会议室数量
	 */
	@Transactional(readOnly=true)
	public int getMeetingConditionCount(Integer meetingId,Integer empId,String empName,String meetingState,String startTime,String meetingTheme, String meetingRoomName,String meetingRoomLocation) {
		return mapper.getMeetingConditionCount(meetingId,empId, empName, meetingState, startTime, meetingTheme, meetingRoomName, meetingRoomLocation);
	}
	
	/**
	 * 全文检索会议
	 * 
	 * @throws ParseException
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException 
	 */
	@Transactional(readOnly = true)
	public List<Meeting> queryMeetingByIndex(String queryStr) throws ParseException, IOException, InvalidTokenOffsetsException {

		// 设置常见停用词（的，么，啊，着之类的东东）
		String[] self_stop_words = { "的", "着", "啊", "么", "嘛" , "是","及" };
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
				new String[] { "meetingId", "meetingTheme", "meetingState" }, analyzer);

		// 创建查询对象
		Query query = queryParser.parse(queryStr);

		// 调用索引dao
		List<Meeting> list = indexDao.search(query);

		return list;
	}

}
