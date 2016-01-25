package com.geewaza.study.test.web.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by WangHeng on 2016/1/25.
 */
public class IndexUtil {

	private String[] ids= {"1", "2", "3", "4", "5", "6"};
	private String[] emails = {"aa@itat.org", "bb$itat.org", "cc@cc.org", "dd@sina.org", "ee@zt.org", "secf@youku.com"};
	private String[] contents = {
			"Do not go gentle into that good night",
			"Old age should burn and  rave at close of day",
			"Rage, rage against the dying of the light",
			"Though wise men at their end know dark is right",
			"Because their words had  forked no lightning they",
			"Do not go gentle into that good night",
	};

	private int[] attachs = {2,3,1,4,5,5};
	private String[] names = {"zhangsan", "lisi", "wangwu", "zhaoliu", "maosan", "gousi"};

	private Directory directory;


	public IndexUtil() {
		try {
			directory = FSDirectory.open(new File("/opt/lucene/index02"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void index() throws IOException {
		IndexWriter writer = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35)));
		Document doc = null;
		for (int i = 0 ; i < ids.length; i++) {
			doc = new Document();
			doc.add(new Field("id", ids[i], Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
			doc.add(new Field("email", emails[i], Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
			doc.add(new Field("content", contents[i], Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
			doc.add(new Field("name", names[i], Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
			doc.add(new NumericField("attach", Field.Store.YES, true).setIntValue(attachs[i]));
			doc.add(new NumericField("date", Field.Store.YES, true).setLongValue(new Date().getTime()));
			writer.addDocument(doc);
		}
		writer.close();
	}

	public void query() throws IOException {
		IndexReader reader = IndexReader.open(directory);
		System.out.println("numDocs:" + reader.numDocs());
		System.out.println("maxDocs:" + reader.maxDoc());
		System.out.println("deleteDocs:" + reader.numDeletedDocs());
		reader.close();
	}

 }
