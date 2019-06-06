package com.icss.oa.card.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
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

import com.icss.oa.card.dao.CardMapper;
import com.icss.oa.card.index.CardIndexDao;
import com.icss.oa.card.pojo.Card;
import com.icss.oa.common.Pager;

/**
 * 名片服务类
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CardService {

	@Autowired
	private CardMapper mapper;

	@Autowired
	private CardIndexDao indexDao;

	// 增加数据
	public void addCard(Card card) {
		mapper.insert(card);
		mapper.upTeamNum(card.getTeam().getTeamId());

		// 获得插入名片的自动编号
		int cardId = mapper.getLastInsertId();

		try {
			/********** 生成索引 *************/
			// 创建索引文档
			Document document = new Document();
			document.add(new TextField("cardId", String.valueOf(cardId), Store.YES));
			document.add(new TextField("cardName", card.getCardName(), Store.YES));
			document.add(new TextField("cardSex", card.getCardSex(), Store.YES));
			document.add(new TextField("cardPhone", card.getCardPhone(), Store.YES));
			document.add(new TextField("cardCareer", card.getCardCareer(), Store.YES));
			document.add(new TextField("cardAddress", card.getCardAddress(), Store.YES));
			document.add(new TextField("cardIntro", card.getCardIntro(), Store.YES));

			// 调用索引dao
			indexDao.create(document);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 删除数据
	public void deleteCard(Integer cardId) {
	
		int id0 = mapper.queryTeamIdById(cardId);
		mapper.delete(cardId);
		mapper.lowTeamNum(id0);

		// 索引
		try {
			Term term = new Term("cardId", String.valueOf(cardId));
			// 调用索引dao
			indexDao.delete(term);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 修改数据
	public void updateCard(Integer cardId, Card card) {
		int id0 = mapper.queryTeamIdById(cardId);
		int id = card.getTeam().getTeamId();
		mapper.update(card);
		mapper.upTeamNum(id);
		mapper.lowTeamNum(id0);

		// 索引
		try {
			Term term = new Term("cardId", String.valueOf(card.getCardId()));

			// 创建索引文档
			Document document = new Document();
			document.add(new TextField("cardId", String.valueOf(card.getCardId()), Store.YES));
			document.add(new TextField("cardName", card.getCardName(), Store.YES));
			document.add(new TextField("cardSex", card.getCardSex(), Store.YES));
			document.add(new TextField("cardPhone", card.getCardPhone(), Store.YES));
			document.add(new TextField("cardCareer", card.getCardCareer(), Store.YES));
			document.add(new TextField("cardAddress", card.getCardAddress(), Store.YES));
			document.add(new TextField("cardIntro", card.getCardIntro(), Store.YES));

			// 调用索引dao
			indexDao.update(term, document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 通过id查询数据
	@Transactional(readOnly = true)
	public Card queryCardById(Integer cardId) {
		return mapper.queryById(cardId);
	}

	// 条件获取记录数
	@Transactional(readOnly = true)
	public Integer getCardCountByCondition(Integer teamId, String cardName, String cardSex, String cardIntro) {
		return mapper.getCountByCondition(teamId, cardName, cardSex, cardIntro);
	}

	// 条件查询(分页、分组名称、姓名、性别、介绍)
	@Transactional(readOnly = true)
	public List<Card> queryCardByCondition(Pager page, Integer teamId, String cardName, String cardSex,
			String cardIntro) {
		return mapper.queryByCondition(page.getStart(), page.getPageSize(), teamId, cardName, cardSex, cardIntro);
	}
	
	/**
	 * 全文检索名片
	 * 
	 * @throws ParseException
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException 
	 */
	@Transactional(readOnly = true)
	public List<Card> queryCardByIndex(String queryStr) throws ParseException, IOException, InvalidTokenOffsetsException {

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
				new String[] { "cardId", "cardName", "cardSex", "cardPhone", "cardCareer", "cardAddress", "cardIntro"}, analyzer);

		// 创建查询对象
		Query query = queryParser.parse(queryStr);

		// 调用索引dao
		List<Card> list = indexDao.search(query);

		return list;
	}

}
