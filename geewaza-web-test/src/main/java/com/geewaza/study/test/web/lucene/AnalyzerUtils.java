package com.geewaza.study.test.web.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by wangh on 2016/2/20.
 */
public class AnalyzerUtils {

	public static void displayTokens(Analyzer analyzer, String text) throws IOException {
		displayTokens(analyzer.tokenStream("contents,", new StringReader(text)));
	}

	public static void displayTokens(TokenStream tokenStream) throws IOException {
		TermAttribute term = tokenStream.addAttribute(TermAttribute.class);
		while (tokenStream.incrementToken()) {
			System.out.print("[" + term.term() + "]");
		}
	}
}
