package com.geewaza.study.test.web.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
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

	public void index() {
		IndexWriter writer = null;
		try {
//			Directory directory = new RAMDirectory();
			Directory directory = FSDirectory.open(new File("/opt/lucene/index01"));
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35));
			writer = new IndexWriter(directory, iwc);

			Document doc = null;
			File f = new File("/opt/lucene/example");
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
}
