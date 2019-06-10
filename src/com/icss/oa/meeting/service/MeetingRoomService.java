package com.icss.oa.meeting.service;
import java.awt.print.Paper;
import java.io.IOException;
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
import com.icss.oa.meeting.dao.MeetingRoomMapper;
import com.icss.oa.meeting.index.MeetingRoomIndexDao;
import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingRoom;
import com.icss.oa.system.index.EmpIndexDao;
import com.icss.oa.system.pojo.Employee;


/**
 * 会议室业务
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)//加入事物
public class MeetingRoomService {
	
	@Autowired
	private MeetingRoomMapper mapper;
	
	@Autowired
	private MeetingRoomIndexDao indexDao;
	
	//增加会议室
	
	public void addMeetingRoom(MeetingRoom meetingRoom){
		mapper.insert(meetingRoom);
		
		// 获得插入会议室的自动编号
				int meetingRoomId = mapper.getLastInsertId();

				try {
					/********** 生成索引 *************/
				    // 创建索引文档
 				    Document document = new Document();
					document.add(new TextField("meetingRoomId", String.valueOf(meetingRoomId), Store.YES));
					document.add(new TextField("meetingRoomName", meetingRoom.getMeetingRoomName(), Store.YES));
					document.add(new TextField("meetingRoomLocation", meetingRoom.getMeetingRoomLocation(), Store.YES));
					document.add(new TextField("meetingRoomCondition", meetingRoom.getMeetingRoomCondition(), Store.YES));
					document.add(new TextField("meetingRoomSize", String.valueOf(meetingRoom.getMeetingRoomSize()), Store.YES));

					// 调用索引dao
					indexDao.create(document);
				} catch (IOException e) {
					e.printStackTrace();
				}
		
	}
	
	/**
	 * 修改会议室
	 */
	public void updateMeetingRoom(MeetingRoom meetingRoom) {
		mapper.update(meetingRoom);
		
		// 索引
				try {
					Term term = new Term("meetingRoomId", String.valueOf(meetingRoom.getMeetingRoomId()));

					// 创建索引文档
					Document document = new Document();
					document.add(new TextField("meetingRoomId", String.valueOf(meetingRoom.getMeetingRoomId()), Store.YES));
					document.add(new TextField("meetingRoomName", meetingRoom.getMeetingRoomName(), Store.YES));
					document.add(new TextField("meetingRoomLocation", meetingRoom.getMeetingRoomLocation(), Store.YES));
					document.add(new TextField("meetingRoomCondition", meetingRoom.getMeetingRoomCondition(), Store.YES));
					document.add(new TextField("meetingRoomSize", String.valueOf(meetingRoom.getMeetingRoomSize()), Store.YES));

					// 调用索引dao
					indexDao.update(term, document);
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

	/**
	 * 删除会议室
	 */
	public void deleteMeetingRoom(Integer meetingRoomId) {
		mapper.delete(meetingRoomId);
		
		// 索引
				try {
					Term term = new Term("meetingRoomId", String.valueOf(meetingRoomId));
					// 调用索引dao
					indexDao.delete(term);
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
	
	/**
	 * 通过id查询会议室
	 */
	@Transactional(readOnly=true)
	public MeetingRoom queryMeetingRoomById(Integer meetingRoomId) {
		return mapper.queryById(meetingRoomId);
	}
	
	/**
	 * 查询所有会议室
	 */
	@Transactional(readOnly=true)
	public List<MeetingRoom> queryMeetingRoom() {
		return mapper.query();
	}
	
	/**
	 * 条件查询会议室
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<MeetingRoom> queryByCondition(Pager pager,Integer meetingRoomId,String meetingRoomName,String meetingRoomState,String meetingRoomLocation,String meetingRoomCondition,Integer meetingRoomSize) {
		return mapper.queryByCondition(pager.getStart(), pager.getPageSize() , meetingRoomId, meetingRoomName, meetingRoomState, meetingRoomLocation, meetingRoomCondition, meetingRoomSize);
	}
	
	/**
	 * 获得会议室数量
	 * @return
	 */
	public int getMeetingRoomCount() {
		return mapper.getMeetingRoomCount();
	}
	
	/**
	 * 分页查询所有会议室
	 */
	@Transactional(readOnly=true)
	public List<MeetingRoom> queryMeetingRoomByPage(Pager pager) {
		return mapper.queryByPage2(pager.getStart(),pager.getPageSize());
	}
	
	/**
	 * 获得符合条件的会议室数量
	 */
	@Transactional(readOnly=true)
	public int getMeetingRoomConditionCount(Integer meetingRoomId,
			                       String meetingRoomName,
			                       String meetingRoomState,
			                       String meetingRoomLocation,
			                       String meetingRoomCondition,
			                       Integer meetingRoomSize) {
		return mapper.getMeetingRoomConditionCount(meetingRoomId,meetingRoomName,meetingRoomState,meetingRoomLocation,meetingRoomCondition,meetingRoomSize);
	}
	
	/**
	 * 全文检索会议室
	 * 
	 * @throws ParseException
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException 
	 */
	@Transactional(readOnly = true)
	public List<MeetingRoom> queryMeetingRoomByIndex(String queryStr) throws ParseException, IOException, InvalidTokenOffsetsException {

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
				new String[] { "meetingRoomId", "meetingRoomName", "meetingRoomLocation", "meetingRoomCondition","meetingRoomSize" }, analyzer);

		// 创建查询对象
		Query query = queryParser.parse(queryStr);

		// 调用索引dao
		List<MeetingRoom> list = indexDao.search(query);

		return list;
	}

}