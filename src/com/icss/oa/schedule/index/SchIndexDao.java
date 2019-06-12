package com.icss.oa.schedule.index;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.icss.oa.schedule.pojo.Schedule;

@Repository
public class SchIndexDao {


	@Value("#{prop.sch_index_path}")
	private String schIndexPath;
	public Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47);

	/**
	 * 
	 * @throws IOException
	 */
	public void create(Document document) throws IOException {
		
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, analyzer);
	
		Directory directory = FSDirectory.open(new File(schIndexPath));

		IndexWriter indexWriter = new IndexWriter(directory, config);
		indexWriter.addDocument(document);
	
		indexWriter.commit();
	
		indexWriter.close();
	}

	/**
	 *
	 * 
	 * @throws IOException
	 */
	public void update(Term term, Document document) throws IOException {
	
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, analyzer);
		Directory directory = FSDirectory.open(new File(schIndexPath));
		
		IndexWriter indexWriter = new IndexWriter(directory, config);
		
		indexWriter.updateDocument(term, document);
	
		indexWriter.commit();
		
		indexWriter.close();
	}

	/**
	 * 
	 * 
	 * @throws IOException
	 */
	public void delete(Term term) throws IOException {
		
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, analyzer);
		
		Directory directory = FSDirectory.open(new File(schIndexPath));
	
		IndexWriter indexWriter = new IndexWriter(directory, config);
		
		indexWriter.deleteDocuments(term);
		
		indexWriter.commit();
	
		indexWriter.close();
	}

	/**
	 * 
	 * 
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException 
	 */
	public List<Schedule> search(Query query) throws IOException, InvalidTokenOffsetsException {

		
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, analyzer);
		
		Directory directory = FSDirectory.open(new File(schIndexPath));

		
		IndexReader indexReader = DirectoryReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);

		
		Filter filter = null;

		Sort sort = new Sort();

		TopDocs topDocs = indexSearcher.search(query, 100, sort);

	
		int recordCount = topDocs.totalHits;

		ArrayList<Schedule> schList = new ArrayList<>();

	
		
		SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<font color=red>", "</font>");
		
		Highlighter highlighter = new Highlighter(formatter, new QueryScorer(query));
		
		highlighter.setTextFragmenter(new SimpleFragmenter(100));
		

		
		for (int i = 0; i < recordCount; i++) {
			
			ScoreDoc scoreDoc = topDocs.scoreDocs[i];
			
			int docSn = scoreDoc.doc;
	
			Document document = indexSearcher.doc(docSn);

		
			Integer schId = Integer.parseInt(document.get("schId"));
		
			String schName = document.get("schName");
			
			String schInfo = document.get("schInfo");
			

			TokenStream tream = analyzer.tokenStream("schInfo",	new StringReader(schInfo));
			String schInfoFragment = highlighter.getBestFragment(tream, schInfo);
			
	
			tream = analyzer.tokenStream("schName",	new StringReader(schName));
			String schNameFragment = highlighter.getBestFragment(tream, schName);
			
			
			if (schInfoFragment == null) {
				int minLen = schInfo.length() >= 100 ? 100 : schInfo.length();
				schInfoFragment = schInfo.substring(0, minLen);				
			}		
			
			if (schNameFragment == null) {
				int minLen = schName.length() >= 100 ? 100 : schName.length();
				schNameFragment = schName.substring(0, minLen);				
			}
			

			Schedule sch = new Schedule();

			sch.setSchId(schId);
			sch.setSchName(schNameFragment);
			sch.setSchInfo(schInfoFragment);
			schList.add(sch);
		}

		return schList;
	}

}