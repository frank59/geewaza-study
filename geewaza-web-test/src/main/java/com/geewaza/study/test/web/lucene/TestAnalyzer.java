package com.geewaza.study.test.web.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.util.Version;

import java.io.IOException;

/**
 * Created by wangh on 2016/2/26.
 */
public class TestAnalyzer {

	public static void main(String[] args) throws IOException {
		test04();
	}

	private static void test04() throws IOException {
		Analyzer a1 = new MyStopAnalyzer(new String[]{"I", "you", "hate"});
		Analyzer a2 = new MyStopAnalyzer();
		String text = "how are you thank you I hate you";
		AnalyzerUtils.displayTokens(a1, text);
		AnalyzerUtils.displayTokens(a2, text);
	}
}
