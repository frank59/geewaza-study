package com.geewaza.study.test.web.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

import java.io.IOException;

/**
 * Created by wangh on 2016/2/20.
 */
public class AnalyzerDemo {

	private static final String[] examples = {
			"The quick brown fox jumped over the lazy dog",
			"XT&Z Corporation - xyz@example.com",
			"No Fluff, Just Stuff"
	};

	private static final Analyzer[] analyzers = new Analyzer[]{
			new WhitespaceAnalyzer(),
			new SimpleAnalyzer(),
			new StopAnalyzer(Version.LUCENE_30),
			new StandardAnalyzer(Version.LUCENE_30)
	};

	private static void analyze(String text) throws IOException {
		System.out.println("Analyzing \"" + text + "\"");
		for (Analyzer analyzer : analyzers) {
			String name = analyzer.getClass().getSimpleName();
			System.out.println("  " + name + ":");
			System.out.print("    ");
			AnalyzerUtils.displayTokens(analyzer, text);
			System.out.println("\n");
		}
	}

	public static void main(String[] args) throws IOException {
		String[] strings = examples;
		if (args.length > 0) {
			strings = args;
		}
		for (String text : strings) {
			analyze(text);
		}
	}
}
