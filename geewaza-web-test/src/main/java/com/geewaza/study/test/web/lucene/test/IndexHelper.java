package com.geewaza.study.test.web.lucene.test;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import java.io.IOException;

/**
 * Created by WangHeng on 2016/8/1.
 */
public class IndexHelper {

    private static Directory m_directory;

    private static Directory getRAMDirectory() {
        if (m_directory == null) {
            m_directory = new RAMDirectory();
        }
        return m_directory;
    }

    private static IndexWriter createIndexWriter(Directory directory) throws IOException {
        IndexWriter writer = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35)));
        return writer;
    }
}
