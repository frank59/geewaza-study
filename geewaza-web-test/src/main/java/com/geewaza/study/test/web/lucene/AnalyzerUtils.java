package com.geewaza.study.test.web.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.*;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by wangh on 2016/2/20.
 */
public class AnalyzerUtils {

	public static void displayTokens(Analyzer analyzer, String text) throws IOException {
		displayTokens(analyzer.tokenStream("随便指定", new StringReader(text)));
	}

	public static void displayTokens(TokenStream tokenStream) throws IOException {
		TermAttribute term = tokenStream.addAttribute(TermAttribute.class);
		while (tokenStream.incrementToken()) {
			System.out.print("[" + term.term() + "]");
		}
		System.out.println();
	}

	public static void displayAllTokenInfo(String str,Analyzer a) {
		try {
			TokenStream stream = a.tokenStream("content",new StringReader(str));
			//位置增量的属性，存储语汇单元之间的距离
			PositionIncrementAttribute pia =
					stream.addAttribute(PositionIncrementAttribute.class);
			//每个语汇单元的位置偏移量
			OffsetAttribute oa;
			oa = stream.addAttribute(OffsetAttribute.class);
			//存储每一个语汇单元的信息（分词单元信息）
			CharTermAttribute cta =
					stream.addAttribute(CharTermAttribute.class);
			//使用的分词器的类型信息
			TypeAttribute ta =
					stream.addAttribute(TypeAttribute.class);
			for(;stream.incrementToken();) {
				System.out.print(pia.getPositionIncrement()+":");
				System.out.print(cta+"["+oa.startOffset()+"-"+oa.endOffset()+"]-->"+ta.type()+"\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
