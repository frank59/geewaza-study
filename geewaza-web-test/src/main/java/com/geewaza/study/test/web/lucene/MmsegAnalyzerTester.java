package com.geewaza.study.test.web.lucene;

import com.chenlb.mmseg4j.MMSeg;
import com.chenlb.mmseg4j.SimpleSeg;
import com.chenlb.mmseg4j.analysis.ComplexAnalyzer;
import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;
import com.chenlb.mmseg4j.analysis.SimpleAnalyzer;
import com.chenlb.mmseg4j.example.Simple;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by wangh on 2016/2/26.
 */
public class MmsegAnalyzerTester {

	public static void main(String[] args) throws IOException {
		test01();
	}

	private static void test01() throws IOException {
		String text = "生活不止眼前的苟且，还有诗和远方的田野。你赤手空拳来到人世间，为找到那片海不顾一切。";
		AnalyzerUtils.displayTokens(new MMSegAnalyzer(), text);
		AnalyzerUtils.displayTokens(new SimpleAnalyzer(), text);
		AnalyzerUtils.displayTokens(new ComplexAnalyzer(), text);
	}
}
