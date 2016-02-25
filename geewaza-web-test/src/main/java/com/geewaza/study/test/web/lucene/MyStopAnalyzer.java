package com.geewaza.study.test.web.lucene;

import org.apache.lucene.analysis.*;
import org.apache.lucene.util.Version;

import java.io.Reader;
import java.util.Set;

/**
 * Created by wangh on 2016/2/25.
 */
public class MyStopAnalyzer extends Analyzer{
	private Set<Object> stops;
	public MyStopAnalyzer(String[] sws) {
		stops = StopFilter.makeStopSet(Version.LUCENE_35, sws, true);
		stops.addAll(StopAnalyzer.ENGLISH_STOP_WORDS_SET);
	}

	public MyStopAnalyzer(){
		stops = (Set<Object>) StopAnalyzer.ENGLISH_STOP_WORDS_SET;
	}

	@Override
	public TokenStream tokenStream(String fieldName, Reader reader) {
		return new StopFilter(Version.LUCENE_35,
				new LowerCaseFilter(Version.LUCENE_35,
				new LetterTokenizer(Version.LUCENE_35, reader)), stops);
	}

}
