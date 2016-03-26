package com.geewaza.study.test.web.solr;

import org.apache.commons.io.FileUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by WangHeng on 2016/3/25.
 */
public class InsertVideoData {

    public static void main(String[] args) throws IOException, SolrServerException {
        test01();
    }

    private static void test01() throws IOException, SolrServerException {

        String fileName = "/tmp/videos.dat";

        List<String> lines = FileUtils.readLines(new File(fileName));

        HttpSolrServer server = new HttpSolrServer("http://localhost:8080/solr/videos");
        for (String line : lines) {
            JSONObject videoJSON = new JSONObject(line);

            int id = videoJSON.getInt("id");
            String title = videoJSON.getString("title");
            String thumburl = videoJSON.getString("thumburl");
            String playurl = videoJSON.getString("playurl");
            String tag1 = videoJSON.getString("tag1");
            String tag2 = videoJSON.getString("tag2");
            String tag3 = videoJSON.getString("tag3");


            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("id", id);
            doc.addField("title", title);
            doc.addField("thumburl", thumburl);
            doc.addField("playurl", playurl);
            doc.addField("tag_1", tag1);
            doc.addField("tag_2", tag2);
            doc.addField("tag_3", tag3);
            server.add(doc);
        }
        server.commit();
        System.out.println("OK!");
        System.exit(0);
    }

}
