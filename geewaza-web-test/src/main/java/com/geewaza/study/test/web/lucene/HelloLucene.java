package com.geewaza.study.test.web.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by WangHeng on 2016/1/18.
 */
public class HelloLucene {

	private static final String SRC_FILE = "/opt/lucene/example";
	private static final String DIRECTORY_PATH = "/opt/lucene/index01";

	public void index() {
		IndexWriter writer = null;
		try {
//			Directory directory = new RAMDirectory();
			Directory directory = FSDirectory.open(new File(DIRECTORY_PATH));
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35));
			writer = new IndexWriter(directory, iwc);

			Document doc = null;
			File f = new File(SRC_FILE);
			for (File file : f.listFiles()) {
				doc = new Document();
				doc.add(new Field("content", new FileReader(file)));
				doc.add(new Field("filename", file.getName(), Field.Store.YES, Field.Index.NOT_ANALYZED));
				doc.add(new Field("path", file.getAbsolutePath(), Field.Store.YES, Field.Index.NOT_ANALYZED));

				writer.addDocument(doc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void searcher() {

		try {
			Directory directory = FSDirectory.open(new File(DIRECTORY_PATH));
			IndexReader reader = IndexReader.open(directory);

			IndexSearcher searcher = new IndexSearcher(reader);
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", new StandardAnalyzer(Version.LUCENE_35));
			Query query = parser.parse("java");

			TopDocs tds = searcher.search(query, 10);
			ScoreDoc[] sds = tds.scoreDocs;
			for (ScoreDoc sd : sds) {
				Document doc = searcher.doc(sd.doc);
				System.out.println(doc.get("filename") + "[" + doc.get("path") + "]");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
