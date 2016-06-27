package com.geewaza.study.test.web.solr;

import org.apache.commons.io.FileUtils;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.FieldAnalysisRequest;
import org.apache.solr.client.solrj.response.AnalysisResponseBase;
import org.apache.solr.client.solrj.response.FieldAnalysisResponse;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by WangHeng on 2016/4/7.
 */
public class AnalysisUtil {

    public static void main(String[] args) throws IOException {

        test02();
        System.out.println("OK!");
        System.exit(0);
    }

    private static void test02() throws IOException {
        String wordFile = "/tmp/topic_info.txt";
        String output = "/tmp/topic_info_analysed.txt";
        HttpSolrServer server = new HttpSolrServer("http://localhost:8080/solr/videos");

        List<String> lines = FileUtils.readLines(new File(wordFile));
        List<String> resultLines = new ArrayList<String>();
        for (String line : lines) {
            String[] lineItems = line.split("\\|");
            String title = lineItems[0];
            String desc = lineItems[1];
            StringBuilder sb = new StringBuilder();
            sb.append(title).append("|").append(desc).append("|");
            Set<String> wordsSet = new HashSet<String>();

            List<String> analysTitle = getAnalysisWord(title, server);
            wordsSet.addAll(analysTitle);
            List<String> analysDesc = getAnalysisWord(desc, server);
            wordsSet.addAll(analysDesc);
            sb.append(wordsSet.toString());
            resultLines.add(sb.toString());
        }

        FileUtils.writeLines(new File(output), resultLines);
    }


    private static List<String> getAnalysisWord(String sentence, HttpSolrServer server) {
        FieldAnalysisRequest request = new FieldAnalysisRequest("/analysis/field");
        request.addFieldName("keyword");
        request.setFieldValue("");
        request.setQuery(sentence);
        FieldAnalysisResponse response = null;
        try {
            response = request.process(server);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Iterator<AnalysisResponseBase.AnalysisPhase> it = response.getFieldNameAnalysis("keyword")
                .getQueryPhases().iterator();
        List<String> result = new ArrayList<String>();
        while(it.hasNext()) {
            AnalysisResponseBase.AnalysisPhase pharse = (AnalysisResponseBase.AnalysisPhase)it.next();
            List<AnalysisResponseBase.TokenInfo> list = pharse.getTokens();
            for (AnalysisResponseBase.TokenInfo info : list) {
                result.add(info.getText());
            }
        }
        return result;
    }

    private static void test01() {

        String[] tests = {
                "泡泡糖的正确玩法",
                "吃泡泡糖最过瘾的就是能吹出个大泡泡,吹得好的小伙伴那是有江湖地位的！一起来围观下牛人是怎么花样吹泡泡糖的吧。",
                "get男神的100种方法",
                "EXO",
                "我是吉他弹唱达人",
        };

        HttpSolrServer server = new HttpSolrServer("http://localhost:8080/solr/videos");

        for (String test : tests) {
            System.out.println("---------------------"+test+"---------------------");
            FieldAnalysisRequest request = new FieldAnalysisRequest("/analysis/field");
            request.addFieldName("keyword");
            request.setFieldValue("");
            request.setQuery(test);
            FieldAnalysisResponse response = null;
            try {
                response = request.process(server);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Iterator<AnalysisResponseBase.AnalysisPhase> it = response.getFieldNameAnalysis("keyword")
                    .getQueryPhases().iterator();
            while(it.hasNext()) {
                AnalysisResponseBase.AnalysisPhase pharse = (AnalysisResponseBase.AnalysisPhase)it.next();
                List<AnalysisResponseBase.TokenInfo> list = pharse.getTokens();
                for (AnalysisResponseBase.TokenInfo info : list) {
                    System.out.println(info.getText());
                }
            }

        }

    }
}
