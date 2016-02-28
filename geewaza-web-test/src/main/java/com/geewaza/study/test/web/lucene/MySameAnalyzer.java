package com.geewaza.study.test.web.lucene;

import com.chenlb.mmseg4j.Dictionary;
import com.chenlb.mmseg4j.MaxWordSeg;
import com.chenlb.mmseg4j.analysis.MMSegTokenizer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;

import java.io.Reader;

/**
 * Created by wangh on 2016/2/28.
 */
public class MySameAnalyzer extends Analyzer {


	@Override
	public TokenStream tokenStream(String s, Reader reader) {
		Dictionary dic = Dictionary.getInstance();
		return new MySameTokenFilter(new MMSegTokenizer(new MaxWordSeg(dic), reader));
	}
}
