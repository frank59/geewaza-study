package com.geewaza.study.test.web.lucene;

import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import java.io.IOException;

/**
 * Created by wangh on 2016/2/26.
 */
public class TestAnalyzer {

	public static void main(String[] args) throws IOException {
		test05();
	}

	private static void test06() throws IOException {
		String text = "生活不止眼前的苟且，还有诗和远方的田野。你赤手空拳来到人世间，为找到那片海不顾一切。";
		AnalyzerUtils.displayTokens(new MMSegAnalyzer(), text);
	}

	private static void test05() throws IOException {
		String text = "生活不止眼前的苟且，还有诗和远方的田野。你赤手空拳来到人世间，为找到那片海不顾一切。";
		
		Directory dir = new RAMDirectory();
		IndexWriter writer = new IndexWriter(dir, new IndexWriterConfig(Version.LUCENE_35, new MySameAnalyzer()));
		Document doc = new Document();
		doc.add(new Field("content", text, Field.Store.YES, Field.Index.ANALYZED));
		writer.addDocument(doc);
		writer.close();

		IndexSearcher searcher = new IndexSearcher(IndexReader.open(dir));
		TopDocs tds = searcher.search(new TermQuery(new Term("content", "面前")), 10);
		Document d = searcher.doc(tds.scoreDocs[0].doc);
		System.out.println(d.get("content"));


	}

	private static void test04() throws IOException {
		Analyzer a1 = new MyStopAnalyzer(new String[]{"I", "you", "hate"});
		Analyzer a2 = new MyStopAnalyzer();
		String text = "how are you thank you I hate you all";
		AnalyzerUtils.displayTokens(a1, text);
		AnalyzerUtils.displayTokens(a2, text);
	}
}
