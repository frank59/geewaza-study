package com.geewaza.study.test.web.lucene.c04;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangh on 2016/3/12.
 */
public class SearchTest {
	private static IndexReader reader = null;
	static {
		try {
			reader = IndexReader.open(FileIndexUtils.getDirectory());
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public IndexSearcher getSearcher() {
		try {
			if(reader==null) {
				reader = IndexReader.open(FileIndexUtils.getDirectory());
			} else {
				IndexReader tr = IndexReader.openIfChanged(reader);
				if(tr!=null) {
					reader.close();
					reader = tr;
				}
			}
			return new IndexSearcher(reader);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void searcherBySort(String queryStr,Sort sort) {
		try {
			IndexSearcher searcher = getSearcher();
			QueryParser parser = new QueryParser(Version.LUCENE_35,"content",new StandardAnalyzer(Version.LUCENE_35));
			Query query = parser.parse(queryStr);
			TopDocs tds = null;
			if(sort!=null)
				tds = searcher.search(query, 50, sort);
			else {
				tds = searcher.search(query, 50);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(ScoreDoc sd:tds.scoreDocs) {
				Document d = searcher.doc(sd.doc);
				System.out.println(sd.doc+":("+sd.score+")" +
						"["+d.get("filename")+"【"+d.get("path")+"】---"+d.get("score")+"--->"+
						d.get("size")+"-----"+sdf.format(new Date(Long.valueOf(d.get("date"))))+"]");
			}
			searcher.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
