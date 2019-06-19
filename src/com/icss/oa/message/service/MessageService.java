package com.icss.oa.message.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.Term;
import org.apache.lucene.document.TextField;
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
import com.icss.oa.message.dao.MessageMapper;
import com.icss.oa.message.index.MessageIndexDao;
import com.icss.oa.message.pojo.Message;
import com.icss.oa.system.dao.EmployeeMapper;
import com.icss.oa.system.pojo.Employee;

/**
 * 在线信息业务层
 * 
 * @author bhl
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MessageService {

	@Autowired
	private MessageMapper mapper;
	
	@Autowired
	private EmployeeMapper empMapper;
	
	@Autowired
	private MessageIndexDao indexDao;

	/**
	 * 根据id查询信息
	 * 
	 * @param mesId
	 * @return
	 */
	public Message queryMesById(Integer mesId) {
		return mapper.queryById(mesId);
	}

	/**
	 * 添加信息
	 * 
	 * @param mes
	 */
	public void addMes(Message mes) {
		mapper.insert(mes);

		// 获得插入员工的自动编号
		int mesId = mapper.getLastInsertId();

		try {
			/********** 生成索引 *************/
			// 创建索引文档
			Document document = new Document();
			document.add(new TextField("mesId", String.valueOf(mesId), Store.YES));
			document.add(new TextField("mesTitle", mes.getMesTitle(), Store.YES));
			document.add(new TextField("mesInfo", mes.getMesInfo(), Store.YES));

			// 调用索引dao
			indexDao.create(document);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除信息
	 * 
	 * @param mesId
	 */
	public void deleteMes(Integer mesId) {
		mapper.delete(mesId);

		// 索引
		try {
			Term term = new Term("mesId", String.valueOf(mesId));
			// 调用索引dao
			indexDao.delete(term);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改信息
	 * 
	 * @param mes
	 */
	public void updateMes(Message mes) {
		mapper.update(mes);

		// 索引
		try {
			Term term = new Term("mesId", String.valueOf(mes.getMesId()));

			// 创建索引文档
			Document document = new Document();
			document.add(new TextField("empId", String.valueOf(mes.getMesId()), Store.YES));
			document.add(new TextField("mesTitle", mes.getMesTitle(), Store.YES));
			document.add(new TextField("mesInfo", mes.getMesInfo(), Store.YES));

			// 调用索引dao
			indexDao.update(term, document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回信息总记录数
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public int getMesCount() {
		return mapper.getCount();
	}

	/**
	 * 全部的模糊查询
	 * 
	 * @param pager
	 * @param mesSendDate
	 * @param empEmail
	 * @param mesTitle
	 * @param mesReciver
	 * @param mesInfo
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Message> queryMesByCondition(Pager pager, String mesSendDate, String empEmail, String mesTitle,
			Integer mesReciver, String mesInfo) {
		return mapper.queryByCondition(pager.getStart(), pager.getPageSize(), mesSendDate, empEmail, mesTitle,
				mesReciver, mesInfo);
	}

	/**
	 * 全部的模糊查询 没有收件人
	 * 
	 * @param pager
	 * @param mesSendDate
	 * @param empEmail
	 * @param mesTitle
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Message> queryMesByCondition1(Pager pager, String mesSendDate, String empEmail, String mesTitle) {
		return mapper.queryByCondition1(pager.getStart(), pager.getPageSize(), mesSendDate, empEmail, mesTitle);
	}

	/**
	 * 根据条件查询总记录数
	 * 
	 * @param mesSendDate
	 * @param empEmail
	 * @param mesTitle
	 * @param mesReciver
	 * @param mesInfo
	 * @return
	 */
	@Transactional(readOnly = true)
	public Integer getMesCountByCondition(String mesSendDate, String empEmail, String mesTitle, Integer mesReciver,
			String mesInfo) {
		return mapper.getCountByCondition(mesSendDate, empEmail, mesTitle, mesReciver, mesInfo);
	}

	/**
	 * 全文检索员工
	 * 
	 * @throws ParseException
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException
	 */
	@Transactional(readOnly = true)
	public List<Message> queryMesByIndex(String queryStr)
			throws IOException, InvalidTokenOffsetsException, ParseException {

		// 设置常见停用词（的，么，啊，着之类的东东）
		String[] self_stop_words = { "的", "着", "啊", "么", "嘛", "是" };
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
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47, cas);

		// 创建查询解析器对象，多字段搜索
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_47,
				new String[] { "mesId", "mesTitle", "mesInfo" }, analyzer);

		// 创建查询对象
		Query query = queryParser.parse(queryStr);

		// 调用索引dao
		List<Message> list = indexDao.search(query);

		return list;
	}

	/**
	 * 根据登录名查询信息
	 * 
	 * @param empLoginName
	 * @param pager
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Message> queryMesByLoginName(String empLoginName, Pager pager, String mesSendDate, String empEmail,
			String mesTitle, Integer mesReciver, String mesInfo) {
		return mapper.queryByLoginName(empLoginName, pager.getStart(), pager.getPageSize(), mesSendDate, empEmail,
				mesTitle, mesReciver, mesInfo);
	}

	/**
	 * 根据登录名查询响应的总记录数
	 * 
	 * @param empLoginName
	 * @return
	 */
	@Transactional(readOnly = true)
	public int getMesCountByEmpLoginName(String empLoginName, String mesSendDate, String empEmail, String mesTitle,
			Integer mesReciver, String mesInfo) {
		return mapper.getCountByEmpLoginName(empLoginName, mesSendDate, empEmail, mesTitle, mesReciver, mesInfo);
	}

	/**
	 * 草稿箱和发件箱查询
	 * 
	 * @param mesSendConfirm
	 * @param start
	 * @param pageSize
	 * @param empLoginName
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Message> queryMesDraft(String mesSendConfirm, Pager pager, String empLoginName) {
		return mapper.queryDraft(mesSendConfirm, pager.getStart(), pager.getPageSize(), empLoginName);
	}

	/**
	 * 草稿箱和发件箱个数
	 * 
	 * @param mesSendConfirm
	 * @param empLoginName
	 * @return
	 */
	@Transactional(readOnly = true)
	public Integer getMesDraftCount(String mesSendConfirm, String empLoginName) {
		return mapper.getCountDraft(mesSendConfirm, empLoginName);
	}

	/**
	 * 收件箱查询
	 * 
	 * @param start
	 * @param pageSize
	 * @param empLoginName
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Message> queryMesInbox(Pager pager, String empLoginName) {
		return mapper.queryInbox(pager.getStart(), pager.getPageSize(), empLoginName);
	}

	/**
	 * 收件箱个数
	 * 
	 * @param empLoginName
	 * @return
	 */
	@Transactional(readOnly = true)
	public Integer getMesInboxCount(String empLoginName) {
		return mapper.getCountInbox(empLoginName);
	}

	/**
	 * 未读消息查询
	 * 
	 * @param pager
	 * @param empLoginName
	 * @param mesSendConfirm
	 * @param mesReadConfirm
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Message> queryMesUnread(String mesSendConfirm, String mesReadConfirm, Pager pager,
			String empLoginName) {
		return mapper.queryUnread(mesSendConfirm, mesReadConfirm, pager.getStart(), pager.getPageSize(), empLoginName);
	}

	/**
	 * 未读消息查询
	 * 
	 * @param empLoginName
	 * @param mesSendConfirm
	 * @param mesReadConfirm
	 * @return
	 */
	@Transactional(readOnly = true)
	public Integer getMesUnreadCount(String mesSendConfirm, String mesReadConfirm, String empLoginName) {
		return mapper.getCountUnread(mesSendConfirm, mesReadConfirm, empLoginName);
	}

	/**
	 * 群发邮件
	 * 
	 * @param mes
	 * @param ids
	 */
	public void groupSend(Message mes, Integer[] ids,String empLoginName) {
		
		Employee sender = empMapper.queryByLoginName(empLoginName);
		mes.setMesSender(sender);
		
		for (int i = 0; i < ids.length; i++) {
			
			Employee mesReciver = new Employee();
			mesReciver.setEmpId(ids[i]);
			mes.setMesReciver(mesReciver);
			
			mapper.insert(mes);
			
			// 获得插入信息的自动编号
			int mesId = mapper.getLastInsertId();

			try {
				/********** 生成索引 *************/
				// 创建索引文档
				Document document = new Document();
				document.add(new TextField("mesId", String.valueOf(mesId), Store.YES));
				document.add(new TextField("mesTitle", mes.getMesTitle(), Store.YES));
				document.add(new TextField("mesInfo", mes.getMesInfo(), Store.YES));

				// 调用索引dao
				indexDao.create(document);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
