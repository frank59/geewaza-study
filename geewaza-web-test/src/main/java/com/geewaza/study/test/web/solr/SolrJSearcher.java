package com.geewaza.study.test.web.solr;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.ModifiableSolrParams;

import java.net.MalformedURLException;

/**
 * Created by WangHeng on 2016/3/23.
 */
public class SolrJSearcher {

    public static void main(String[] args) throws MalformedURLException, SolrServerException {
        HttpSolrServer solr = new HttpSolrServer("http://localhost:8080/solr");

        ModifiableSolrParams params = new ModifiableSolrParams();
        params.set("q", "cat:book");
        params.set("defType", "edismax");
        params.set("start", "0");

        QueryResponse response = solr.query(params);
        SolrDocumentList results = response.getResults();
        for (int i = 0; i < results.size(); ++i) {
            System.out.println(results.get(i));
        }
    }
}
